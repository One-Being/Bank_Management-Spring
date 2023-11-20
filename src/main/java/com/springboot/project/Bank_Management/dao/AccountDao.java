package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.project.Bank_Management.dto.Account;
import com.springboot.project.Bank_Management.repository.AccountRepo;

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
			if (b.getPassword().isBlank()) 
			{
				b.setPassword(ex.getPassword());
				
			}
			if (b.getTransact().isEmpty()) 
			{
				b.setTransact(ex.getTransact());
				
			}
			b.setAccountId(id);
			return repo.save(b);
		}
		return null;
	}

}
