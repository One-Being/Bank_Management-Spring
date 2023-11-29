package com.springboot.project.Bank_Management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.project.Bank_Management.config.ResponseStructure;
import com.springboot.project.Bank_Management.dao.BankDao;
import com.springboot.project.Bank_Management.dao.BranchDao;
import com.springboot.project.Bank_Management.dao.ManagerDao;
import com.springboot.project.Bank_Management.dao.UserDao;
import com.springboot.project.Bank_Management.dto.Bank;
import com.springboot.project.Bank_Management.dto.Branch;
import com.springboot.project.Bank_Management.dto.Manager;
import com.springboot.project.Bank_Management.dto.User;
import com.springboot.project.Bank_Management.exceptions.BankNotFoundException;
import com.springboot.project.Bank_Management.exceptions.BranchNotFoundException;
import com.springboot.project.Bank_Management.exceptions.InvalidManagerLoginException;

import com.springboot.project.Bank_Management.exceptions.UserNotFoundException;

@Service
public class BranchService {

	@Autowired
	BranchDao brdao;
	
	@Autowired
	ManagerDao mdao;
	
	@Autowired
	BankDao bdao;
	
	@Autowired
	UserDao udao;
	
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch b,int bankId) 
	{
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		Bank exBank = bdao.findBank(bankId);
		if(exBank != null) {
		Branch savedBranch = brdao.saveBranch(b);
		exBank.getBranches().add(savedBranch);
		savedBranch.setBank(exBank);
		repost.setData(brdao.updateBranch(savedBranch, savedBranch.getBranchId()));
		repost.setMessage("Branch Has Been Saved");
		repost.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.CREATED );
		}
		throw new BankNotFoundException("Bank Not Found");
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
		throw new BranchNotFoundException("Branch Not Found");
		
	}
	public ResponseEntity<ResponseStructure<Branch>> deleteBranch( int id) 
	{
		ResponseStructure<Branch> repost = new ResponseStructure<>();
		Branch branch = brdao.findBranch(id);
		if (branch!=null) {
			branch.getBank().setBranches(null);
			branch.getManager().setBranch(null);
			brdao.updateBranch(branch, id);
			repost.setData(brdao.deleteBranch(id));
			repost.setMessage("Branch Has Been Deleted");
			repost.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Branch>>(repost,HttpStatus.OK );
		}
		throw new BranchNotFoundException("Branch Not Found");
		
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
			throw new BankNotFoundException("Bank Not Found");
		}
		throw new BranchNotFoundException("Branch Not Found");
	}
	
	public ResponseEntity<ResponseStructure<List<Branch>>>findAllBranch() {
		ResponseStructure<List<Branch>> repost = new ResponseStructure<>();
		if (!brdao.findAll().isEmpty()) {
			
			repost.setData(brdao.findAll());
			repost.setMessage("Branch Found");
			repost.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Branch>>>(repost,HttpStatus.FOUND );
		}
		throw new BranchNotFoundException("Branch Not Found");
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
		throw new BranchNotFoundException("Branch Not Found");
	}
	
	public  ResponseEntity<ResponseStructure<User>> changeBranch(String mname , String mpassword,int uid, int nbid ) {
		Manager man = mdao.loginManager(mname, mpassword);
		ResponseStructure<User> repost = new ResponseStructure<>();
		User u = udao.findUser(uid);
		Branch br = brdao.findBranch(nbid);
		
		if (man != null) 
		{
			if (u != null) 
			{
				if(br != null) {
					if (u.getBranch().getBranchId() != nbid ) {
						
						u.getBranch().getUser().remove(u);
						u.setBranch(br);
						
						br.getUser().add(udao.updateUser(uid, u));
						brdao.updateBranch(br, nbid);
						repost.setData(u);
						repost.setMessage("Branch Has Been Changed");
						repost.setStatus(HttpStatus.ACCEPTED.value());
						return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.ACCEPTED );
					}
					repost.setMessage("User can't changed to same branch");
					repost.setStatus(HttpStatus.BAD_REQUEST.value());
					return new ResponseEntity<ResponseStructure<User>>(repost,HttpStatus.BAD_REQUEST ) ; 
				}
				throw new BranchNotFoundException("Branch Not Found");
			}
			throw new UserNotFoundException("User Not Found");
			
		}
		
		throw new InvalidManagerLoginException("Invalid Login Credentials");
		
	}
	
	
}
