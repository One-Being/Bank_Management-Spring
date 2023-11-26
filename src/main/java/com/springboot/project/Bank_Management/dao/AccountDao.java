package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.project.Bank_Management.dto.Account;
import com.springboot.project.Bank_Management.repository.AccountRepo;

@Repository
public class AccountDao {
	@Autowired
	AccountRepo repo;
	
	public Account saveAccount(Account a)
	{
		return repo.save(a);
	}
	
	public Account findAccount(int id)
	{
		Optional<Account> account = repo.findById(id);
		return account.get() ;
	}
	public Account findAccountByAccountNo(long accno)
	{
		return repo.findAccountNumber(accno);
		
	}
	
	public Account deleteAccount(int id)
	{
		Account acc = findAccount(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<Account> findAllAccount()
	{
		return repo.findAll();
	}
	public Account updateAccount(int id, Account b) {
		Account ex = findAccount(id);
		if(ex != null)
		{
			if (b.getAccountNumber() <= 0) {
				b.setAccountNumber(ex.getAccountNumber());
			}
			
			if (b.getActype() == null) {
				b.setActype(ex.getActype());
			}
			
			if (b.getBalance() <0) 
			{
				b.setBalance(ex.getBalance());
			}
			if (b.getPassword()==null) 
			{
				b.setPassword(ex.getPassword());
				
			}
			if (b.getTransact() == null) 
			{
				b.setTransact(ex.getTransact());
				
			}
			
			if (b.getUser() == null) 
			{
				b.setUser(ex.getUser());
			}
			b.setAccountId(id);
			return repo.save(b);
		}
		return null;
	}
	
	public Account loginAccount(int id, String password) 
	{
		
		Account acc= findAccount(id);
		if (acc.getPassword().equals(password)) 
		{
			return acc;
			
		}
		
		return null;
	}
	

}
