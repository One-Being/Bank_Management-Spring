package com.springboot.project.Bank_Management.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class Branch 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int branchId;
	private String ifsc;
	private String name;
	@JsonBackReference
	@ManyToOne
	private Bank bank;
	@JsonBackReference
	@OneToOne
	private Manager manager;
	@OneToMany
	private List<User> user;
	@OneToOne
	private Address address;
	

}
