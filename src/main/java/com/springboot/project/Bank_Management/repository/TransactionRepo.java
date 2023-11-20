package com.springboot.project.Bank_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.Bank_Management.dto.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {

}
