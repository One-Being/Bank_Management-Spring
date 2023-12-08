package com.springboot.project.Bank_Management.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;


@Configuration
public class Config implements WebMvcConfigurer
{

	@Bean
	public OpenAPI swaggerDocOpenApi() 
	{
		Server devserver = new Server();
		devserver.setUrl("");
		devserver.setDescription("Development Server");
		Server testserver = new Server();
		testserver.setUrl("");
		testserver.setDescription("TestServer");
		
		Contact co = new Contact();
		co.setEmail("aravindgokul90@gmai;l");	
		co.setName("Gokul");
		co.setUrl("../https://github.com/One-Being");
		
		License ls = new License();
		ls.setName("License");
		ls.setUrl("license providers");
		
		Info in = new Info();
		in.setContact(co);
		in.setLicense(ls);
		in.setDescription("Project Description: Bank Management System\r\n"
				+ "\r\n"
				+ "The Bank Management System is a comprehensive Java-based application developed using the Spring Boot framework. This system is designed to streamline and automate the operations of a banking institution, offering efficient management of various entities such as banks, branches, managers, users, accounts, and transactions.\r\n"
				+ "\r\n"
				+ "Key Components:\r\n"
				+ "\r\n"
				+ "Entities:\r\n"
				+ "\r\n"
				+ "Bank: Represents the main banking institution.\r\n"
				+ "Branch: Represents individual branches associated with a bank.\r\n"
				+ "Manager: Represents the managers overseeing specific branches.\r\n"
				+ "User: Represents customers and other users interacting with the system.\r\n"
				+ "Account: Represents individual bank accounts associated with users.\r\n"
				+ "Transaction: Represents the financial transactions occurring within the system.\r\n"
				+ "Enums:\r\n"
				+ "\r\n"
				+ "AccountType: Defines the type of account (e.g., savings, checking).\r\n"
				+ "TransactionType: Enumerates the types of transactions (e.g., deposit, withdrawal).\r\n"
				+ "TransactionStatus: Enumerates the status of a transaction (e.g., pending, completed).\r\n"
				+ "Methods:\r\n"
				+ "\r\n"
				+ "Save: Allows the addition of new records for entities (e.g., new bank, branch, user, account, transaction).\r\n"
				+ "Update: Enables modification of existing entity details (e.g., update account information, branch details).\r\n"
				+ "Delete: Allows the removal of records (e.g., close an account, delete a user).\r\n"
				+ "Retrieve: Facilitates the retrieval of information from the system (e.g., retrieve user details, transaction history).\r\n"
				+ "Key Features:\r\n"
				+ "\r\n"
				+ "User Management: Efficiently manage customer information, including account creation, updates, and deletion.\r\n"
				+ "Transaction Handling: Securely process and record financial transactions with different types and statuses.\r\n"
				+ "Branch and Bank Management: Maintain a structured hierarchy of banks, branches, and managers for effective organization.\r\n"
				+ "Data Persistence: Utilize Spring Boot's capabilities to ensure reliable data storage and retrieval.\r\n"
				+ "Security: Implement secure access controls and authentication mechanisms to protect sensitive financial data.\r\n"
				+ "\nTechnology Stack:\r\n"
				+ "\r\n"
				+ "Language: Java\r\n"
				+ "Framework: Spring Boot\r\n"
				+ "Database:MySQL\r\n"
				+ "Version Control: Git\r\n"
				+ "Build Tool: Maven\r\n"
				+ "Environment: Eclipse\r\n"
				+ "This Bank Management System aims to enhance the efficiency, security, and organization of banking operations, providing a robust solution for managing various entities and their interactions within the financial ecosystem.");
		in.setTermsOfService("../Terms&Con.html");
		in.setTitle("Bank Management");
		in.setVersion("2.0");
		
		
		OpenAPI op = new OpenAPI();
		op.info(in);
		op.servers(Arrays.asList(devserver,testserver));
		return op;
		
		}
}
