package com.springboot.project.Bank_Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.AddressDao;
import com.springboot.project.Bank_Management.dao.BranchDao;
import com.springboot.project.Bank_Management.dao.ManagerDao;
import com.springboot.project.Bank_Management.dao.UserDao;
import com.springboot.project.Bank_Management.dto.Address;
import com.springboot.project.Bank_Management.dto.Branch;
import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.dto.User;

@Service
public class AddressService {

	@Autowired
	AddressDao adao;
	
	@Autowired
	UserDao udao;
	
	@Autowired
	ManagerDao mdao;
	
	
	@Autowired
	BranchDao bdao;
	
	
	public ResponseEntity<ResponseStructure<User>> createAddressForUser(Address addr, int uid) 
	{
		
		User u = udao.findUser(uid);
		u.setAddress(adao.saveAddress(addr));
		
		ResponseStructure<User> repost = new ResponseStructure<>();
		repost.setData(udao.updateUser(uid, u));
		repost.setMessage("Address has Been assignedto user ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<Branch>> createAddressForBranch(Address addr, int uid) 
	{
		
		Branch u = bdao.findBranch(uid);
		u.setAddress(adao.saveAddress(addr));
		
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		repost.setData(bdao.updateBranch(u,uid));
		repost.setMessage("Address has Been assignedto Branch ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<Manager>> createAddressForManager(Address addr, int uid) 
	{
		
		Manager u = mdao.findManager(uid);
		u.setAddress(adao.saveAddress(addr));
		
		ResponseStructure<Manager> repost = new ResponseStructure<>();
		repost.setData(mdao.updateManager(uid, u));
		repost.setMessage("Address has Been assignedto Manager ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Manager>>(repost,HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address addr, int aid)
	{
		
		ResponseStructure<Address> repost = new ResponseStructure<>();
		repost.setData(adao.updateAddress(aid, addr));
		repost.setMessage("Address has Been Updated ");
		repost.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Address>>(repost,HttpStatus.OK);
		
		
	} 
	
	
}
