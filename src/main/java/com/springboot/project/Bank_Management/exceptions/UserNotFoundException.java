package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
	
	private String message;

	public UserNotFoundException(String message) {
		
		this.message = message;
	}
}
