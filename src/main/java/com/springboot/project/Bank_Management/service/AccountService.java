package com.springboot.project.Bank_Management.service;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.AccountDao;
import com.springboot.project.Bank_Management.dao.UserDao;
import com.springboot.project.Bank_Management.dto.Account;
import com.springboot.project.Bank_Management.dto.AccountType;
import com.springboot.project.Bank_Management.dto.Transaction;
import com.springboot.project.Bank_Management.dto.User;
import com.springboot.project.Bank_Management.exceptions.AccountNotFoundException;
import com.springboot.project.Bank_Management.exceptions.InvalidUserLoginException;


@Service
public class AccountService 


{
	
	@Autowired
	AccountDao dao;
	
	@Autowired
	UserDao udao;
	
	public ResponseEntity<ResponseStructure<Account>> updateAccount(Account acc , int aid) {
		
	if (dao.findAccount(aid) != null)
	{
	
	ResponseStructure<Account> repost = new ResponseStructure<>();
		
		repost.setData(dao.updateAccount(aid, acc));
		repost.setMessage("Account Has Been Updated ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.OK);
	}
	throw new AccountNotFoundException("Account Not Found");
		
	}
	
	public ResponseEntity<ResponseStructure<Account>>  changingAccountType(int accid, int acctype ) 
	{
		ResponseStructure<Account> repost = new ResponseStructure<>();
		Account acc = dao.findAccount(accid) ;
		if (dao.findAccount(accid) != null) 
		{
			if (acctype == 1  ) 
			{
				if (acc.getActype() == AccountType.SAVING) 
				{
				acc.setActype(AccountType.CURRENT);
				repost.setData(dao.updateAccount(accid, acc));
				repost.setMessage("Account Type Has Been Changed ");
				repost.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.OK);
				}
				
				else {
					repost.setMessage("You Can't Change Account Type From current to Current ");
					repost.setStatus(HttpStatus.BAD_REQUEST.value());
					
					return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.BAD_REQUEST);
				}
			}
			
			if (acctype == 2) 
			{

				
				if (acc.getActype() == AccountType.CURRENT) 
				{
				
				acc.setActype(AccountType.SAVING);
				repost.setData(dao.updateAccount(accid, acc));
				repost.setMessage("Account Type Has Been Changed ");
				repost.setStatus(HttpStatus.OK.value());
				
				return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.OK);
				}
				
				else {
					repost.setMessage("You Can't Change Account Type From saving to saving ");
					repost.setStatus(HttpStatus.BAD_REQUEST.value());
					
					return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.BAD_REQUEST);
				}
				
			}
			
			else {
				repost.setMessage("Invaild Input");
				repost.setStatus(HttpStatus.BAD_REQUEST.value());
				
				return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.BAD_REQUEST);
				
			}
			
		}
		throw new AccountNotFoundException("Account Not Found");
	}
	
	public ResponseEntity<ResponseStructure<Account>> findAccountByAccNo(long accno) 
	{
		ResponseStructure<Account> repost = new ResponseStructure<>();
		if (dao.findAccountByAccountNo(accno) != null) {
			repost.setData(dao.findAccountByAccountNo(accno));
			repost.setMessage("Account Has Been Found");
			repost.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.FOUND);
		}

		throw new AccountNotFoundException("Account Not Found");
	}
	public ResponseEntity<ResponseStructure<List<Transaction>>> transaction(long  accno)
	{
		if (dao.findAccountByAccountNo(accno) != null) 
		{
			List<Transaction> tra = dao.findAccountByAccountNo(accno).getTransact();
			ResponseStructure<List<Transaction>> repost = new ResponseStructure<>();
			repost.setData(tra);
			repost.setMessage("Transactions Found ");
			repost.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Transaction>>>(repost,HttpStatus.FOUND);
			
		}
		throw new AccountNotFoundException("Account Not Found");
	} 
	
	public ResponseEntity<ResponseStructure<List<Transaction>>> getTransactionBetween(String uname, String upassword, int month) 
	{
		ResponseStructure<List<Transaction>> res = new ResponseStructure<>();

		User u = udao.userLogin(uname, upassword);
		if (u != null) {
			
				Account a = u.getAccount();
				List<Transaction> tran = a.getTransact();
				
				List<Transaction> td = new ArrayList<>();

				for (Transaction transaction : tran) {
					LocalDate transactionDate = transaction.getTransactionTime().toLocalDate();
					LocalDate now = LocalDate.now();
					LocalDate then = now.minusMonths(month);

					
					if (transactionDate.isAfter(then) && transactionDate.isBefore(now)) {
						
						
						td.add(transaction);
					}
					
					res.setData(td);
					res.setMessage("List of Transaction Last "+month+" Month");
					res.setStatus(HttpStatus.FOUND.value());
					
				}
				return new ResponseEntity<ResponseStructure<List<Transaction>>>(res, HttpStatus.FOUND);
				


		} else {
			throw new InvalidUserLoginException("Invalid Login Credentials");
		}
		
	}

}
