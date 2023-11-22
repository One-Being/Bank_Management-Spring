package com.springboot.project.Bank_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dto.Bank;
import com.springboot.project.Bank_Management.service.BankService;

@RestController
@RequestMapping("/bank")
public class BankController 
{
	@Autowired
	BankService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Bank>> saveBank(@RequestBody Bank bank, @RequestParam int brid) 
	{
		return service.saveBank(bank,brid);
		
	}
	
	@DeleteMapping
	public  ResponseEntity<ResponseStructure<Bank>> deleteBook(@RequestParam int id) 
	{
		return service.deleteBank(id);
	}
	
	@PutMapping
	public  ResponseEntity<ResponseStructure<Bank>> updateBank(@RequestBody Bank b, @RequestParam int id) 
	{
		return service.updateBank(b,id);
		
	}
	
	@GetMapping
	public  ResponseEntity<ResponseStructure<Bank>> findBank(@RequestParam int id) 
	{
		return service.findBank(id);
	}
	
	@PutMapping("/assignbranch")
	public  ResponseEntity<ResponseStructure<Bank>> assignBranchToBank( @RequestParam int bankId , @RequestParam int branchId ) 
	{
		return service.assignBranchToBank(bankId,branchId);
		
	}
	

}
