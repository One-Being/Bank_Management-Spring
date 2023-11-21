package com.springboot.project.Bank_Management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.BankDao;
import com.springboot.project.Bank_Management.dao.BranchDao;
import com.springboot.project.Bank_Management.dto.Bank;
import com.springboot.project.Bank_Management.dto.Branch;

@Service
public class BranchService {

	@Autowired
	BranchDao brdao;
	
	@Autowired
	BankDao bdao;
	
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch b) 
	{
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		repost.setData(brdao.saveBranch(b));
		repost.setMessage("Branch Has Been Saved");
		repost.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.CREATED );
	}
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(Branch b , int id) 
	{
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		if (brdao.findBranch(id)!=null) {
		repost.setData(brdao.updateBranch(b,id));
		repost.setMessage("Branch Has Been Updated");
		repost.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.OK );
		}
		return null ;//throws Branch not found ex
		
	}
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch( int id) 
	{
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		if (brdao.findBranch(id)!=null) {
			repost.setData(brdao.deleteBranch(id));
			repost.setMessage("Branch Has Been Deleted");
			repost.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.OK );
		}
		return null ;//throws Branch not found ex
		
	}
	public ResponseEntity<ResponseStructure<Branch>>  assignBankToBranch(int branchId ,int bankId) 
	{
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		if (brdao.findBranch(branchId)!=null) {
			if (bdao.findBank(bankId)!=null) {
				Branch branch = brdao.findBranch(branchId);
				branch.setBank(bdao.findBank(bankId));
				repost.setData(brdao.updateBranch(branch,branchId));
				repost.setMessage("Bank Has Been Assigned to Branch");
				repost.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.OK ); 
			}
			return null; //Bank Not found 
		}
		return null; //Branch not found
	}
	
	public ResponseEntity<ResponseStructure<List<Branch>>>findAllBranch() {
		ResponseStructure<List<Branch>> repost = new ResponseStructure<>();
		if (!brdao.findAll().isEmpty()) {
			
			repost.setData(brdao.findAll());
			repost.setMessage("Branch Found");
			repost.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Branch>>>(repost,HttpStatus.FOUND );
		}
		 return null ;//throw new BranchNotFoundException;
	}
	public ResponseEntity<ResponseStructure<Branch>> findBranch(int id) 
	{
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		if (brdao.findBranch(id)!=null) {
		repost.setData(brdao.findBranch(id));
		repost.setMessage("Branch Has Been Found");
		repost.setStatus(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.FOUND );
		}
		return null ;//throws Branch not found ex
	}
	
	
}
