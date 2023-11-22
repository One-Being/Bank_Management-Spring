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
import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	ManagerService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Manager>> saveManager(@RequestBody Manager manager ,  @RequestParam int branchId) 
	{
		return service.saveManager(manager,branchId);
		
	}
	
	@DeleteMapping
	public  ResponseEntity<ResponseStructure<Manager>> deleteBook(@RequestParam int id) 
	{
		return service.deleteManager(id);
	}
	
	@PutMapping
	public  ResponseEntity<ResponseStructure<Manager>> updateManager(@RequestBody Manager b, @RequestParam int id) 
	{
		return service.updateManager(b,id);
		
	}
	
	@GetMapping
	public  ResponseEntity<ResponseStructure<Manager>> findManager(@RequestParam int id) 
	{
		return service.findManager(id);
	}
}
