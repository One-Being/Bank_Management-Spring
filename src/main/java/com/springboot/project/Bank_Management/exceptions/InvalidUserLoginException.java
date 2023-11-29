package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class InvalidUserLoginException extends RuntimeException {
	
	
	private String message;	
	public InvalidUserLoginException(String message){
		this.message = message;
	}
	
}
