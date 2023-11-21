package com.springboot.project.Bank_Management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.BankDao;
import com.springboot.project.Bank_Management.dto.Bank;

@Service
public class BankService {

	@Autowired
	BankDao bdao;
	
	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank b) 
	{
		ResponseStructure<Bank> repost = new ResponseStructure<>();
		repost.setData(bdao.saveBank(b));
		repost.setMessage("Bank Has Been Saved");
		repost.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Bank>>(repost,HttpStatus.CREATED );
	}
	public ResponseEntity<ResponseStructure<Bank>> updateBank(Bank b , int id) 
	{
		ResponseStructure<Bank> repost = new ResponseStructure<>();
		if (bdao.findBank(id)!=null) {
		repost.setData(bdao.updateBank(b,id));
		repost.setMessage("Bank Has Been Updated");
		repost.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Bank>>(repost,HttpStatus.OK );
		}
		return null ;//throws bank not found ex
		
	}
	
	public ResponseEntity<ResponseStructure<List<Bank>>>findAllBank() {
		ResponseStructure<List<Bank>> repost = new ResponseStructure<>();
		if (!bdao.findAll().isEmpty()) {
			
			repost.setData(bdao.findAll());
			repost.setMessage("Bank Found");
			repost.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Bank>>>(repost,HttpStatus.FOUND );
		}
		 return null ;//throw new BankNotFoundException("Bank not found ");
	}
	public ResponseEntity<ResponseStructure<Bank>> findBank(int id) 
	{
		ResponseStructure<Bank> repost = new ResponseStructure<>();
		if (bdao.findBank(id)!=null) {
		repost.setData(bdao.findBank(id));
		repost.setMessage("Bank Has Been Updated");
		repost.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Bank>>(repost,HttpStatus.OK );
		}
		return null ;//throws bank not found ex
	}
		
	
}
