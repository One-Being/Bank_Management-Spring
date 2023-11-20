package com.springboot.project.Bank_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.Bank_Management.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer> {

}
