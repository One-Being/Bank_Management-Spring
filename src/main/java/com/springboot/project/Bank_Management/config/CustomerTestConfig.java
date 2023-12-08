package com.springboot.project.Bank_Management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class CustomerTestConfig
{
	@Bean
	   public WebClient webClient() {
	
		 Builder client =  WebClient.builder();
		 WebClient client2 = client.build();
	       return client2;
	   }

}
