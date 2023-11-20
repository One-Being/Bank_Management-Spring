package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.springboot.project.Bank_Management.dto.Bank;
import com.springboot.project.Bank_Management.repository.BankRepo;

public class BankDao {

	@Autowired
	BankRepo repo;
	
	@Autowired
	Bank bnk;
	
	public Bank saveBank( Bank b) 
	{
		return repo.save(b);
	}
	
	public Bank findBank(int id) 
	{
		Optional<Bank> opBank = repo.findById(id);
		if (opBank.isPresent()) 
		{
			return opBank.get();
		}
		 return null;
	}
	
	public Bank deleteBank(int id) 
	{
		Bank exBank = findBank(id);
		
		if (exBank != null) 
		{
			repo.delete(exBank);
			return exBank;
		}
		 return null;
	}
	
	public List<Bank> findAll() {
		List <Bank> Banklist = repo.findAll();
		return  Banklist;
	}
	
	public Bank updateBank(Bank b , int id) 
	{
		Bank exBank = findBank(id);
		if (exBank != null) 
		{
			if (b.getName()==(null)) 
			{
				b.setName(exBank.getName());
				
			}
			if(b.getContact()<=0) {
				
				b.setContact(exBank.getContact());
			}
			if (b.getBranches() == null) 
			{
				b.setBranches(exBank.getBranches());
				
			}
			b.setBankId(id);
			return repo.save(b);
		}
		 return null;
	}
	
	
	
}
