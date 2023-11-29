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
import com.springboot.project.Bank_Management.exceptions.AddressNotFoundException;
import com.springboot.project.Bank_Management.exceptions.BranchNotFoundException;
import com.springboot.project.Bank_Management.exceptions.ManagerNotFoundException;
import com.springboot.project.Bank_Management.exceptions.UserNotFoundException;

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
		if(u != null) {
		u.setAddress(adao.saveAddress(addr));
		
		ResponseStructure<User> repost = new ResponseStructure<>();
		repost.setData(udao.updateUser(uid, u));
		repost.setMessage("Address has Been assignedto user ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.OK);
		}
		throw new UserNotFoundException("User Not Found");
	}
	
	public ResponseEntity<ResponseStructure<Branch>> createAddressForBranch(Address addr, int uid) 
	{
		
		Branch u = bdao.findBranch(uid);
		if(u != null) {
		u.setAddress(adao.saveAddress(addr));
		
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		repost.setData(bdao.updateBranch(u,uid));
		repost.setMessage("Address has Been assignedto Branch ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.OK);
		}
		throw new BranchNotFoundException("Branch Not Found");
		
	}
	
	public ResponseEntity<ResponseStructure<Manager>> createAddressForManager(Address addr, int uid) 
	{
		
		Manager u = mdao.findManager(uid);
		if(u != null) {
		u.setAddress(adao.saveAddress(addr));
		
		ResponseStructure<Manager> repost = new ResponseStructure<>();
		repost.setData(mdao.updateManager(uid, u));
		repost.setMessage("Address has Been assignedto Manager ");
		repost.setStatus(HttpStatus.OK.value());
		
		return new ResponseEntity<ResponseStructure<Manager>>(repost,HttpStatus.OK);
		}
		throw new ManagerNotFoundException("Manager Not Found");
		
	}
	
	public ResponseEntity<ResponseStructure<Address>> updateAddress(Address addr, int aid)
	{
		if(adao.findAddress(aid)!=null) {
		ResponseStructure<Address> repost = new ResponseStructure<>();
		repost.setData(adao.updateAddress(aid, addr));
		repost.setMessage("Address has Been Updated ");
		repost.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Address>>(repost,HttpStatus.OK);
		}
		throw new AddressNotFoundException("Address Not Found");
		
	} 
	
	
}
