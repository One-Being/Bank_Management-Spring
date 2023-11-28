package com.springboot.project.Bank_Management.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.AccountDao;
import com.springboot.project.Bank_Management.dao.TransactionDao;
import com.springboot.project.Bank_Management.dto.Account;
import com.springboot.project.Bank_Management.dto.Transaction;
import com.springboot.project.Bank_Management.dto.TransactionStatus;
import com.springboot.project.Bank_Management.dto.TransactionType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Service
public class TransactionService {

	@Autowired
	TransactionDao tdao;
	
	@Autowired
	AccountDao acdao;
	
	
	
	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(Transaction t, long accno , String password)
	{
		ResponseStructure<Transaction> repost = new ResponseStructure<>();
		Account acc = acdao.loginAccount(accno, password);
		Account toacc = acdao.findAccountByAccountNo(t.getToAccount());
		if(acc!= null) {
			if(toacc != null) {
				if( acc.getBalance()-100 >= t.getAmount()) {
					if(acc != toacc) {
						if(t.getAmount() > 0) {
						t.setType(TransactionType.DEBITED);
						t.setTransactionTime(LocalDateTime.now());
						
						t.setStatus(TransactionStatus.SUCCESSFUL);	
						Transaction ta = tdao.saveTransaction(t);
						acc.getTransact().add(ta);
						acc.setBalance(acc.getBalance()-ta.getAmount());
						acdao.updateAccount(acc.getAccountId(), acc);
					
						Transaction tr = new Transaction();
						tr.setAmount(ta.getAmount());
						tr.setStatus(TransactionStatus.SUCCESSFUL);
						tr.setTransactionTime(ta.getTransactionTime());
						tr.setType(TransactionType.CREDITED);
						tr.setToAccount(acc.getAccountNumber());
						toacc.getTransact().add(tdao.saveTransaction(tr));
						toacc.setBalance(toacc.getBalance()+ta.getAmount());
						acdao.updateAccount(toacc.getAccountId(), toacc);
						repost.setData(ta);
						repost.setMessage("Transaction Successful");
						repost.setStatus(HttpStatus.OK.value());
						return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.OK);
						}
					}
					repost.setMessage("Cannot transfer to self");
					repost.setStatus(HttpStatus.BAD_REQUEST.value());
					return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.BAD_REQUEST);
				}
				
				t.setType(null);
				t.setTransactionTime(LocalDateTime.now());
				t.setStatus(TransactionStatus.FAILED);	
				Transaction ta = tdao.saveTransaction(t);
				acc.getTransact().add(ta);
				
				acdao.updateAccount(acc.getAccountId(), acc);
				repost.setData(ta);
				repost.setMessage("Insufficient Balance");
				repost.setStatus(HttpStatus.BAD_REQUEST.value());
				return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.BAD_REQUEST);
		}
			repost.setMessage("To account Doesnot Exist");
			repost.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.BAD_REQUEST);
		}
		repost.setMessage("Invaild Login Credentials");
		repost.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.BAD_REQUEST);
	}
}