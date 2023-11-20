package com.springboot.project.Bank_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.Bank_Management.dto.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
