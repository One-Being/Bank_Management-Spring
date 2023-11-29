package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class AddressNotFoundException extends RuntimeException {
	
	private String message;

	public AddressNotFoundException(String message) {
		
		this.message = message;
	}
}
