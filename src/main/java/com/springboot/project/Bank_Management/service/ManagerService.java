package com.springboot.project.Bank_Management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.springboot.project.Bank_Management.config.ResponseStructure;

import com.springboot.project.Bank_Management.dao.BranchDao;
import com.springboot.project.Bank_Management.dao.ManagerDao;


import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.exceptions.BranchNotFoundException;
import com.springboot.project.Bank_Management.exceptions.InvalidManagerLoginException;
import com.springboot.project.Bank_Management.exceptions.ManagerNotFoundException;
import com.springboot.project.Bank_Management.repository.ManagerRepo;

@Service
public class ManagerService {

	@Autowired
	ManagerDao brdao;
	
	@Autowired
	BranchDao bdao;
	
	@Autowired
	ManagerRepo repo;
	
	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager b,int branchId) 
	{
		ResponseStructure<Manager> repost = new ResponseStructure<>();
		Manager savedManager = brdao.saveManager(b);
		if(bdao.findBranch(branchId)!= null) {
		bdao.findBranch(branchId).setManager(savedManager);
		savedManager.setBranch(bdao.findBranch(branchId));
		repost.setData(brdao.updateManager(savedManager.getManagerId(),savedManager));
		repost.setMessage("Manager Has Been Saved");
		repost.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Manager>>(repost,HttpStatus.CREATED );
		}
		throw new BranchNotFoundException("Branch Not Found");
	}
	public ResponseEntity<ResponseStructure<Manager>> updateManager(Manager b , int id) 
	{
		ResponseStructure<Manager> repost = new ResponseStructure<>();
		if (brdao.findManager(id)!=null) {
		repost.setData(brdao.updateManager(id,b));
		repost.setMessage("Manager Has Been Updated");
		repost.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Manager>>(repost,HttpStatus.OK );
		}
		throw new ManagerNotFoundException("Manager Not Found");
		
		
	}
	public ResponseEntity<ResponseStructure<Manager>> deleteManager(int id) 
	{
		ResponseStructure<Manager> repost = new ResponseStructure<>();
		if (brdao.findManager(id)!=null) {
			brdao.findManager(id).getBranch().setManager(null);
			brdao.findManager(id).setBranch(null);
			brdao.updateManager(id,brdao.findManager(id));
			repost.setData(brdao.deleteManager(id));
			repost.setMessage("Manager Has Been Deleted");
			repost.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Manager>>(repost,HttpStatus.OK );
		}
		throw new ManagerNotFoundException("Manager Not Found");
		
	}
	
	
	public ResponseEntity<ResponseStructure<List<Manager>>>findAllManager() {
		ResponseStructure<List<Manager>> repost = new ResponseStructure<>();
		if (!brdao.findAllManager().isEmpty()) {
			
			repost.setData(brdao.findAllManager());
			repost.setMessage("Manager Found");
			repost.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Manager>>>(repost,HttpStatus.FOUND );
		}
		throw new ManagerNotFoundException("Manager Not Found");
	}
	
	
	public ResponseEntity<ResponseStructure<Manager>> findManager(int id) 
	{
		ResponseStructure<Manager> repost = new ResponseStructure<>();
		if (brdao.findManager(id)!=null) {
		repost.setData(brdao.findManager(id));
		repost.setMessage("Manager Has Been Found");
		repost.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Manager>>(repost,HttpStatus.FOUND );
		}
		throw new ManagerNotFoundException("Manager Not Found");
	}
	
	public ResponseEntity<ResponseStructure<Manager>>  loginManager(String name , String password) 
	{
		Manager man = repo.loginMananger(name);
		ResponseStructure<Manager> repost = new ResponseStructure<>();
		if(man != null) {
		if (man.getName() != null) 
		{
			if (man.getPassword().equals(password)) 
			{
				repost.setData(man);
				repost.setMessage("Login Successfull");
				repost.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Manager>>(repost,HttpStatus.OK );
				
			}
			throw new InvalidManagerLoginException("Invalid Login Credentials");
			
		}
		throw new InvalidManagerLoginException("Invalid Login Credentials");
		}
		throw new ManagerNotFoundException("Manager Not Found");
		
	}
	
	
}
