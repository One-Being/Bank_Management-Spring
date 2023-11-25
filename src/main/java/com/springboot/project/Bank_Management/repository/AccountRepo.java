package com.springboot.project.Bank_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.project.Bank_Management.dto.Account;

public interface AccountRepo extends JpaRepository<Account, Integer>
{
    @Query("select a from Account a where a.accountNumber= ?1")
    public Account findAccountNumber(long accno);
}
