package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class TransactionNotFoundException extends RuntimeException {
	
	private String message;

	public TransactionNotFoundException(String message) {
		
		this.message = message;
	}
}
