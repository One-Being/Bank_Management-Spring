package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class BankNotFoundException extends RuntimeException {
	
	private String message;

	public BankNotFoundException(String message) {
		
		this.message = message;
	}
}
