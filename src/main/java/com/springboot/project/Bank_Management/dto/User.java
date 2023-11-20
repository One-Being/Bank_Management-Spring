package com.springboot.project.Bank_Management.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String uname;
	@OneToOne
	private Address address;
	@OneToOne
	private Account account;
	@ManyToOne
	private Branch branch;

}
