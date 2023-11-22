package com.springboot.project.Bank_Management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.BankDao;
import com.springboot.project.Bank_Management.dao.BranchDao;
import com.springboot.project.Bank_Management.dto.Bank;
import com.springboot.project.Bank_Management.dto.Branch;

@Service
public class BankService {

	@Autowired
	BankDao bdao;
	
	@Autowired
	BranchDao brdao;
	
	public ResponseEntity<ResponseStructure<Bank>> saveBank(Bank b, int branchId) 
	{
		ResponseStructure<Bank> repost = new ResponseStructure<>();
		Branch ex = brdao.findBranch(branchId);
		Bank saveBank = bdao.saveBank(b);
			if (brdao.findBranch(branchId)!=null) {
				brdao.updateBranch(ex, branchId);
				List<Branch> brlist = new ArrayList<>();
				saveBank.setBranches(brlist);
				saveBank = bdao.updateBank(saveBank, saveBank.getBankId());
				repost.setData(saveBank);
				repost.setMessage("Bank Has Been  Saved and branch assigned");
				repost.setStatus(HttpStatus.CREATED.value());
				return new ResponseEntity<ResponseStructure<Bank>>(repost,HttpStatus.CREATED );
			}
			repost.setData(saveBank);
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
	public ResponseEntity<ResponseStructure<Bank>> deleteBank( int id) 
	{
		ResponseStructure<Bank> repost = new ResponseStructure<>();
		if (bdao.findBank(id)!=null) {
			repost.setData(bdao.deleteBank(id));
			repost.setMessage("Bank Has Been Deleted");
			repost.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bank>>(repost,HttpStatus.OK );
		}
		return null ;//throws bank not found ex
		
	}
	public ResponseEntity<ResponseStructure<Bank>>  assignBranchToBank(int bankId ,int branchId) 
	{
		ResponseStructure<Bank> repost = new ResponseStructure<>();
		if (bdao.findBank(bankId)!=null) {
			if (brdao.findBranch(branchId)!=null) {
				
				 List<Branch> lis = new ArrayList<>();
				 lis.add(brdao.findBranch(branchId));
				 Bank bank =  bdao.findBank(bankId);
				 bank.setBranches(lis);
				 repost.setData(bdao.updateBank(bank,bankId));
				repost.setMessage("Branch Has Been Assigned to Bank");
				repost.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Bank>>(repost,HttpStatus.OK ); 
			}
			return null; //Branch Not found Exception
		}
		return null; //bank not found exception
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
		repost.setMessage("Bank Has Been Found");
		repost.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Bank>>(repost,HttpStatus.FOUND );
		}
		return null ;//throws bank not found ex
	}
		
	
}
