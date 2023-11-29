package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class ManagerNotFoundException extends RuntimeException {
	
	private String message;

	public ManagerNotFoundException(String message) {
		
		this.message = message;
	}
}
