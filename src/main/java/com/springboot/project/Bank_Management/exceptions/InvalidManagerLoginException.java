package com.springboot.project.Bank_Management.exceptions;

import lombok.Getter;

@Getter
public class InvalidManagerLoginException extends RuntimeException
{

	private String message;	
	public InvalidManagerLoginException(String message){
		this.message = message;
	}
}
