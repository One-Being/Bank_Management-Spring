package com.springboot.project.Bank_Management.dto;

import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;


import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@UniqueElements
	@NotBlank
	@NotNull
	private String ifsc;
	@NonNull
	@NotBlank
	private String name;
	@JsonIgnore
	@ManyToOne
	private Bank bank;
    @JsonIgnore
	@OneToOne
	private Manager manager;
	@OneToMany
	private List<User> user;
	@OneToOne
	private Address address;
	

}
