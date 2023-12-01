package com.springboot.project.Bank_Management.dto;


import java.time.LocalDateTime;


import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Setter
@Getter
public class Transaction 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	private int amount;
	@Min(value = 6)
	private long toAccount;
	private LocalDateTime transactionTime;
	private TransactionType type;
	private TransactionStatus status;
	
	

}
