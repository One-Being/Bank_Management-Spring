package com.springboot.project.Bank_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dto.Account;

import com.springboot.project.Bank_Management.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService service;
	
	@PostMapping
	public  ResponseEntity<ResponseStructure<Account>> updateAccount(@RequestBody Account acc ,@RequestParam int id ){
		return service.updateAccount(acc, id);
	}
	
	@PutMapping
	public  ResponseEntity<ResponseStructure<Account>> changeAccountType(@RequestParam int id ,@RequestParam int acctype ){
		return service.changingAccountType(id, acctype);
	}
	
	@GetMapping
	public  ResponseEntity<ResponseStructure<Account>> findAccountByAccountNumber(long accno) 
	{
		return service.findAccountByAccNo(accno);
	}
	
	
	
	
	
}
