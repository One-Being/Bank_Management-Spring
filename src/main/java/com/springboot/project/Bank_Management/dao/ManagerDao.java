package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.repository.ManagerRepo;

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
			b.setManagerId(id);
			return repo.save(b);
		}
		return null;
	}

}
