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
		repost.setMessage("User Not Verified");
		repost.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.NOT_ACCEPTABLE);
		
	}
}
