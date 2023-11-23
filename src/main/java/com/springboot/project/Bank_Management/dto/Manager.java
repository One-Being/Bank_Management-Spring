package com.springboot.project.Bank_Management.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class Manager {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int managerId;
	private String name;
	private String password;
	@OneToOne
	private Address address;
	
	@OneToOne
	private Branch branch;
}