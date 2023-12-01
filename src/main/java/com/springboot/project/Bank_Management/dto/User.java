package com.springboot.project.Bank_Management.dto;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
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
	@UniqueElements
	@NotBlank
	private String uname;
	@OneToOne
	private Address address;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Account account;
	@JsonIgnore
	@ManyToOne
	private Branch branch;

}
