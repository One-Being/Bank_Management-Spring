package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class BranchNotFoundException extends RuntimeException {
	
	private String message;

	public BranchNotFoundException(String message) {
		
		this.message = message;
	}
}
