package com.springboot.project.Bank_Management.exceptions;

public class InsufficientBalanceException extends RuntimeException 
{
	private String message;	
	public InsufficientBalanceException(String message){
		this.message = message;
	}

}
