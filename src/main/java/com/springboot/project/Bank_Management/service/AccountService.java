package com.springboot.project.Bank_Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.AccountDao;
import com.springboot.project.Bank_Management.dto.Account;
import com.springboot.project.Bank_Management.dto.AccountType;
import com.springboot.project.Bank_Management.dto.User;

@Service
public class AccountService 


{
	
	@Autowired
	AccountDao dao;
	
	public ResponseEntity<ResponseStructure<Account>> updateAccount(Account acc , int aid) {
		
		ResponseStructure<Account> repost = new ResponseStructure<>();
		
		repost.setData(dao.updateAccount(aid, acc));
		repost.setMessage("Account Has Been Updated ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.OK);
		
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
		return null;
	}
	
	public ResponseEntity<ResponseStructure<Account>> findAccountByAccNo(long accno) 
	{
		ResponseStructure<Account> repost = new ResponseStructure<>();
		if (dao.findAccountByAccountNo(accno) != null) {
			repost.setData(dao.findAccountByAccountNo(accno));
			repost.setMessage("Account Has Been Found");
			repost.setStatus(HttpStatus.FOUND.value());
			
			return new ResponseEntity<ResponseStructure<Account>>(repost,HttpStatus.FOUND);
		}return null;
	}

}
