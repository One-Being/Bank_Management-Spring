package com.springboot.project.Bank_Management.dto;


import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@NotBlank
	@NotNull
	private String name;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;
	@OneToOne
	private Address address;
	@OneToOne
	private Branch branch;
}