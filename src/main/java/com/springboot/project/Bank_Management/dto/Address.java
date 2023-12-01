package com.springboot.project.Bank_Management.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	private String street;
	@Min(value = 6000000000l)
	@Max(value = 9999999999l)
	private long contact;
	private String city;
	private String state;
	@Min(value = 6)
	private long pincode;

}
