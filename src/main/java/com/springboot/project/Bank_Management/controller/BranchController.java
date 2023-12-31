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

import com.springboot.project.Bank_Management.dto.Branch;
import com.springboot.project.Bank_Management.dto.User;
import com.springboot.project.Bank_Management.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/branch")
public class BranchController 
{
	@Autowired
	BranchService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestBody @Valid Branch Branch ,  @RequestParam int branchId) 
	{
		return service.saveBranch(Branch,branchId);
		
	}
	
	@DeleteMapping
	public  ResponseEntity<ResponseStructure<Branch>> deleteBook(@RequestParam int id) 
	{
		return service.deleteBranch(id);
	}
	
	@PutMapping
	public  ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestBody @Valid Branch b, @RequestParam int id) 
	{
		return service.updateBranch(b,id);
		
	}
	
	@GetMapping
	public  ResponseEntity<ResponseStructure<Branch>> findBranch(@RequestParam int id) 
	{
		return service.findBranch(id);
	}
	
	@PutMapping("/assignbank")
	public  ResponseEntity<ResponseStructure<Branch>> assignBranchToBank( @RequestParam int bankId ,@RequestParam int branchId ) 
	{
		return service.assignBankToBranch(branchId,bankId);
		
	}
	
	@PutMapping("/changebranch")
	public  ResponseEntity<ResponseStructure<User>> changeBranch(@RequestParam String mname ,@RequestParam String mpassword ,@RequestParam int uid , @RequestParam int bid ){
		return service.changeBranch(mname, mpassword, uid, bid);
	}
	
	

}
