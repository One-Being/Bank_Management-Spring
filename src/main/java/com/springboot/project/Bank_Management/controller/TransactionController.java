package com.springboot.project.Bank_Management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dto.Transaction;
import com.springboot.project.Bank_Management.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	TransactionService service ;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Transaction>> send(@RequestBody Transaction t , @RequestParam int accid, @RequestParam String password) 
	{
		return service.sendMoney(t, accid,password);
	}
	
}
