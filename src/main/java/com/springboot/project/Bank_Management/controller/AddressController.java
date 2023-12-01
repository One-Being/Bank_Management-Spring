package com.springboot.project.Bank_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dto.Address;
import com.springboot.project.Bank_Management.dto.Branch;
import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.dto.User;
import com.springboot.project.Bank_Management.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController 
{
	@Autowired
	AddressService service;
	
	@PostMapping
	public  ResponseEntity<ResponseStructure<User>> createAddressForUser(@RequestBody @Valid Address add , int uid ){
		return service.createAddressForUser(add, uid);
	}
	
	@PostMapping("/createforbranch")
	public  ResponseEntity<ResponseStructure<Branch>> createAddressForBranch(@RequestBody @Valid Address add , int bid ){
		return service.createAddressForBranch(add, bid);
	}
	@PostMapping("/createformanager")
	public  ResponseEntity<ResponseStructure<Manager>> createAddressForManager(@RequestBody @Valid Address add , int mid ){
		return service.createAddressForManager(add, mid);
	}
	@PutMapping
	public  ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody @Valid Address add , int aid ){
		return service.updateAddress(add, aid);
	}
	
	

}
