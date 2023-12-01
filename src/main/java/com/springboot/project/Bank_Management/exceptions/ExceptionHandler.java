package com.springboot.project.Bank_Management.exceptions;


import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.project.Bank_Management.config.ResponseStructure;


@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bankNotFound(BankNotFoundException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("Bank Not Found");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> branchNotFound(BranchNotFoundException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("Branch Not Found");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ManagerNotFound(ManagerNotFoundException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("Manager Not Found");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFound(UserNotFoundException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("User Not Found");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> AccountNotFound(AccountNotFoundException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("Account Not Found");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> transactionNotFound(TransactionNotFoundException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("Transaction Not Found");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
		public ResponseEntity<ResponseStructure<String>> addressNotFound(AddressNotFoundException ex) 
		{
			ResponseStructure<String> repost = new ResponseStructure<>();
			repost.setData("Address Not Found");
			repost.setMessage(ex.getMessage());
			repost.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
			
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> invalidUserLogin(InvalidUserLoginException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("Invalid Login Credentials");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
		
	}
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> invalidManagerLogin(InvalidManagerLoginException ex) 
	{
		ResponseStructure<String> repost = new ResponseStructure<>();
		repost.setData("Invalid Login Credentials");
		repost.setMessage(ex.getMessage());
		repost.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(repost,HttpStatus.NOT_FOUND );
		
	}
	
@Override
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		     List<ObjectError> error =  ex.getAllErrors();
		     HashMap<String, String> map = new HashMap<>();
		     for (ObjectError obError : error) {
				String message = obError.getDefaultMessage();
				String fieldname = ((FieldError) obError).getField();
				map.put(fieldname, message);
			}
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}
	
	
}
	