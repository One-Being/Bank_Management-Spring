package com.springboot.project.Bank_Management.dto;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class Bank
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bankId; 
	@NotBlank(message = "Name Should Not Be Blank")
	@NotNull(message = "Name Should  Not Be Null")
	private String name;
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long contact;
	@JsonIgnore
	@OneToMany
	private List<Branch> branches;

}
