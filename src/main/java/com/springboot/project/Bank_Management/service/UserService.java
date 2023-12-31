package com.springboot.project.Bank_Management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.ManagerDao;
import com.springboot.project.Bank_Management.dao.UserDao;
import com.springboot.project.Bank_Management.dto.Account;
import com.springboot.project.Bank_Management.dto.AccountType;
import com.springboot.project.Bank_Management.dto.Branch;
import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.dto.User;
import com.springboot.project.Bank_Management.exceptions.InvalidManagerLoginException;
import com.springboot.project.Bank_Management.exceptions.InvalidUserLoginException;
import com.springboot.project.Bank_Management.exceptions.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	UserDao udao;
	
	@Autowired
	ManagerDao mdao;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User u , int acctype , String mname , String mpass) 
	{
		ResponseStructure<User> repost = new ResponseStructure<>();
		Manager manager = mdao.loginManager(mname, mpass);
		if (manager != null) 
		{
			Branch branch = manager.getBranch();
			Account acc = new Account();
			switch (acctype) {
			case 1:
			{
				
				acc.setActype(AccountType.CURRENT);break;
			}
			case 2:
			{
				
				acc.setActype(AccountType.SAVING);break;
			}
			default:
				repost.setMessage("Invalid Input");
				repost.setStatus(HttpStatus.BAD_REQUEST.value());
				return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.BAD_REQUEST);
			}
			
			u.setAccount(acc);
			u.setBranch(branch);
			acc.setUser(u);
			
			User savedUser = udao.saveUser(u);
			manager.getBranch().getUser().add(savedUser);
			mdao.updateManager(manager.getManagerId(), manager);
			
			repost.setData(savedUser);
			repost.setMessage("User Has Been Created");
			repost.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.CREATED);
			
		}
		throw new InvalidManagerLoginException("Invalid Login Credentials");
		
	}
	public ResponseEntity<ResponseStructure<User>> findUser(int uid){
		ResponseStructure<User> repost = new ResponseStructure<>();
		if(udao.findUser(uid)  != null) {
			repost.setData(udao.findUser(uid));
			repost.setMessage("User has been found");
			repost.setStatus(HttpStatus.FOUND.value());
			
			return new  ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.FOUND);
		}
		throw new UserNotFoundException("User  Not Found");
	} 
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int uid) 
	{
		ResponseStructure<User> repost = new ResponseStructure<>();
		if (udao.findUser(uid)!= null) 
		{
		
		User u = udao.findUser(uid);
		u.getBranch().getUser().remove(u);
		u.setBranch(null);
		udao.updateUser(uid, u);
		repost.setData(udao.deleteUser(uid));
		repost.setMessage("User Has Been Deleted");
		repost.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.OK );
		}
		
		throw new UserNotFoundException("User  Not Found");
	}
	
	public ResponseEntity<ResponseStructure<User>> userLogin(String uname, String upassword) 
	{
		ResponseStructure<User> repost = new ResponseStructure<>();
		User u = udao.userLogin(uname, upassword);
		if (u != null) 
		{
			repost.setData(u);
			repost.setMessage("Login Successfull");
			repost.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.NOT_FOUND );
		}
		throw new InvalidUserLoginException("Invalid Login Credentials");
	}
	
	
	
	
}
