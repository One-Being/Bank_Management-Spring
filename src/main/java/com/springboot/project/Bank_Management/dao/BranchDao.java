package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.project.Bank_Management.dto.Address;
import com.springboot.project.Bank_Management.dto.Branch;
import com.springboot.project.Bank_Management.repository.AddressRepo;
import com.springboot.project.Bank_Management.repository.BranchRepo;

public class BranchDao {

	@Autowired
	AddressRepo repo;
	
	@Autowired
	Address bnk;
	
	public Address saveAddress( Address b) 
	{
		return repo.save(b);
	}
	
	public Address findAddress(int id) 
	{
		Optional<Address> opAddress = repo.findById(id);
		if (opAddress.isPresent()) 
		{
			return opAddress.get();
		}
		 return null;
	}
	
	public Address deleteAddress(int id) 
	{
		Address exAddress = findAddress(id);
		
		if (exAddress != null) 
		{
			repo.delete(exAddress);
			return exAddress;
		}
		 return null;
	}
	
	public List<Address> findAll() {
		List <Address> Addresslist = repo.findAll();
		return  Addresslist;
	}
	
	public Address updateAddress(Address b , int id) 
	{
		Address exAddress = findAddress(id);
		if (exAddress != null) 
		{
			if (b.getName()==(null)) 
			{
				b.setName(exAddress.getName());
				
			}
			if(b.getUser() == null) {
				
				b.setUser(exAddress.getUser());
			}
			if(b.getAddress() == null) {
				
				b.setAddress(exAddress.getAddress());
			}
			if (b.getIfsc() == null) 
			{
				b.setIfsc(exAddress.getIfsc());
				
			}
			if (b.getBank() == null) 
			{
				b.setBank(exAddress.getBank());
				
			}
			b.setId(id);
			return repo.save(b);
		}
		 return null;
	}
	
	
	
}
