package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.project.Bank_Management.dto.User;
import com.springboot.project.Bank_Management.repository.UserRepo;

@Repository
public class UserDao {
	@Autowired
	UserRepo repo;
	
	
	
	public User saveUser(User a)
	{
		return repo.save(a);
	}
	
	public User findUser(int id)
	{
		Optional<User> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public User deleteUser(int id)
	{
		User acc = findUser(id);
		if(acc != null)
		{
			repo.delete(acc);
			return acc;
		}
		return null;
	}
	public List<User> findAllUser()
	{
		return repo.findAll();
	}
	public User updateUser(int id, User b) {
		User ex = findUser(id);
		if(ex != null)
		{
			if (b.getAccount() == null) 
			{ 
				b.setAccount(ex.getAccount());
			}
			
			if (b.getAddress()==null) 
			{
				b.setAddress(ex.getAddress());
				
			}
			
			if (b.getBranch() == null) 
			{
				b.setBranch(ex.getBranch());
				
			}
			if (b.getUname() == null) {
				b.setUname(ex.getUname());
			}
			b.setUserId(id);
			return repo.save(b);
		}
		return null;
	}
	
	public User userLogin(String uname, String accpassword) 
	{
		User u = repo.findUserByName(uname);
		if(u != null) {
			if(u.getAccount().getPassword().equals(accpassword)) {
				return u;
			}
			return null;
		}
		return null;
		
		
	}

}
