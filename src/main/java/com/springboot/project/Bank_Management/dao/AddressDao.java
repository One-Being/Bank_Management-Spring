package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.project.Bank_Management.dto.Address;
import com.springboot.project.Bank_Management.repository.AddressRepo;

public class AddressDao 
{
	@Autowired
	AddressRepo repo;
	
	public Address saveAddress(Address a)
	{
		return repo.save(a);
	}
	
	public Address findAddress(int id)
	{
		Optional<Address> acc = repo.findById(id);
		return acc.get() ;
	}
	
	public Address deleteAddress(int id)
	{
		Address address = findAddress(id);
		if(address != null)
		{
			repo.delete(address);
			return address;
		}
		return null;
	}
	public List<Address> findAllAddress()
	{
		return repo.findAll();
	}
	public Address updateAccount(int id, Address b) {
		Address ex = findAddress(id);
		if(ex != null)
		{
			b.setAddressId(id);
			return repo.save(b);
		}
		return null;
	}
}
