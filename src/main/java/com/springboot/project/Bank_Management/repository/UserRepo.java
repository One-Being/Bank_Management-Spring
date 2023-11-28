package com.springboot.project.Bank_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.project.Bank_Management.dto.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	
	@Query("select u from User u where u.uname = ?1")
	public User findUserByName(String name);
}
