package com.springboot.project.Bank_Management.service;

import java.time.LocalDate;
import java.util.Date;

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

@Service
public class TransactionService {

	@Autowired
	TransactionDao tdao;
	
	@Autowired
	AccountDao acdao;
	
	public ResponseEntity<ResponseStructure<Transaction>> sendMoney(Transaction t, int aid )
	{
		ResponseStructure<Transaction> repost = new ResponseStructure<>();
		Account acc = acdao.findAccount(aid);
		if(acc!= null) {
			if(acdao.findAccountByAccountNo(t.getToAccount()) != null) {
				if(acc.getBalance()>0 && acc.getBalance() > t.getAmount()) {
					t.setType(TransactionType.DEBITED);
					t.setTransactionTime(LocalDate.now());
					t.setStatus(TransactionStatus.SUCCESSFUL);	
					Transaction ta = tdao.saveTransaction(t);
					acc.getTransact().add(ta);
					acc.setBalance(acc.getBalance()-ta.getAmount());
					acdao.updateAccount(aid, acc);
					Account toacc = acdao.findAccountByAccountNo(ta.getToAccount());
					Transaction tr = new Transaction();
					tr.setAmount(ta.getAmount());
					tr.setStatus(TransactionStatus.SUCCESSFUL);
					tr.setTransactionTime(ta.getTransactionTime());
					tr.setType(TransactionType.CREDITED);
					tr.setToAccount(acc.getAccountNumber());
					toacc.getTransact().add(tdao.saveTransaction(ta));
					toacc.setBalance(toacc.getBalance()+ta.getAmount());
					acdao.updateAccount(toacc.getAccountId(), toacc);
					repost.setData(t);
					repost.setMessage("Transaction Successful");
					repost.setStatus(HttpStatus.OK.value());
					return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.OK);
				}
				repost.setMessage("Insufficient Balance");
				repost.setStatus(HttpStatus.BAD_REQUEST.value());
				return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.BAD_REQUEST);
		}
			repost.setMessage("To account Doesnot Exsit");
			repost.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ResponseStructure<Transaction>>(repost,HttpStatus.BAD_REQUEST);
		}
			return null; //Account not found exception
	}
}
