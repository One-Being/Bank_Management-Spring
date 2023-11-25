package com.springboot.project.Bank_Management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.project.Bank_Management.dto.Address;
import com.springboot.project.Bank_Management.repository.AddressRepo;

@Repository
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
	public Address updateAddress(int id, Address b) {
		Address ex = findAddress(id);
		if(ex != null)
		{
			
			if (b.getContact() <= 0) {
				b.setContact(ex.getContact());
			}
			
			
			if (b.getCity().isBlank()) 
			{
				b.setCity(ex.getCity());
				
			}
			
			if (b.getPincode() <=0)
			{
				b.setPincode(ex.getPincode());
				
			}
			
			if (b.getState() == null) 
			{
				b.setState(ex.getState());
				
			}
			
			if (b.getStreet() == null) 
			{
				b.setStreet(ex.getStreet());
				
				
			}
			
			b.setAddressId(id);
			return repo.save(b);
		}
		return null;
	}
}
