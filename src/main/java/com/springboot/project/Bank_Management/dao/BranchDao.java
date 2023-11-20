package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.springboot.project.Bank_Management.dto.Branch;
import com.springboot.project.Bank_Management.repository.BranchRepo;

public class BranchDao {

	@Autowired
	BranchRepo repo;
	
	@Autowired
	Branch bnk;
	
	public Branch saveBranch( Branch b) 
	{
		return repo.save(b);
	}
	
	public Branch findBranch(int id) 
	{
		Optional<Branch> opBranch = repo.findById(id);
		if (opBranch.isPresent()) 
		{
			return opBranch.get();
		}
		 return null;
	}
	
	public Branch deleteBranch(int id) 
	{
		Branch exBranch = findBranch(id);
		
		if (exBranch != null) 
		{
			repo.delete(exBranch);
			return exBranch;
		}
		 return null;
	}
	
	public List<Branch> findAll() {
		List <Branch> Branchlist = repo.findAll();
		return  Branchlist;
	}
	
	public Branch updateBranch(Branch b , int id) 
	{
		Branch exBranch = findBranch(id);
		if (exBranch != null) 
		{
			if (b.getName()==(null)) 
			{
				b.setName(exBranch.getName());
				
			}
			if(b.getUser() == null) {
				
				b.setUser(exBranch.getUser());
			}
			if(b.getAddress() == null) {
				
				b.setAddress(exBranch.getAddress());
			}
			if (b.getIfsc() == null) 
			{
				b.setIfsc(exBranch.getIfsc());
				
			}
			if (b.getBank() == null) 
			{
				b.setBank(exBranch.getBank());
				
			}
			b.setBranchId(id);
			return repo.save(b);
		}
		 return null;
	}
	
	
	
}
