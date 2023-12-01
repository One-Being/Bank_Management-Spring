package com.springboot.project.Bank_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dto.User;
import com.springboot.project.Bank_Management.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
      UserService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> SaveUser(@RequestBody @Valid User u , @RequestParam int acctype , @RequestParam String mname, @RequestParam String mpassword ) {
		return service.saveUser(u, acctype, mname, mpassword);
		
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<User>> findUser( @RequestParam int id ) {
		return service.findUser(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<User>> deleteUser( @RequestParam int id ) {
		return service.deleteUser(id);
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> userLogin( @RequestParam String uname, @RequestParam String upassword ) 
	{
		return service.userLogin(uname, upassword);
	}
	
	
}
