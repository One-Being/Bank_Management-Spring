package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.repository.ManagerRepo;

@Repository
public class ManagerDao 
{
	@Autowired
	ManagerRepo repo;
	
	public Manager saveManager(Manager a)
	{
		return repo.save(a);
	}
	
	public Manager findManager(int id)
	{
		Optional<Manager> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public Manager deleteManager(int id)
	{
		Manager acc = findManager(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<Manager> findAllManager()
	{
		return repo.findAll();
	}
	public Manager updateManager(int id, Manager b) {
		Manager ex = findManager(id);
		if(ex != null)
		{
			if (b.getAddress()== null)
			{
				b.setAddress(ex.getAddress());
			}
			
			if(b.getBranch() == null) {
				b.setBranch(ex.getBranch());
			}
			if (b.getName() == (null)) 
			{
				b.setName(ex.getName());
				
			}
			if (b.getPassword() == (null)) 
			{
				b.setPassword(ex.getPassword());
				
			}
			
			b.setManagerId(id);
			return repo.save(b);
		}
		return null;
	}

	public Manager  loginManager(String name , String password) 
	{
		Manager man = repo.loginMananger(name);
		
		if (man.getName() != null) 
		{
			if (man.getPassword().equals(password)) 
			{
				
				return man;
				
			}
			return null;
			
		}
		
		
		return null;
		
	}
	
}
