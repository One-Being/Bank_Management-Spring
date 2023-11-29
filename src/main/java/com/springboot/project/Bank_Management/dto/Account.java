package com.springboot.project.Bank_Management.dto;

import java.util.List;

import org.springframework.stereotype.Component;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity

@Setter
@Getter
@Component
public class Account 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountNumber; 
	private String password;
	private AccountType actype;
	private long balance;
	@OneToOne 
	private User user;
	@OneToMany
	private List<Transaction> transact;
	
	

}
