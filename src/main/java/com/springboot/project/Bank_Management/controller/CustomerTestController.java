package com.springboot.project.Bank_Management.controller;



import java.net.URI;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.reactive.function.client.WebClient;

import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/customer")
public class CustomerTestController {

	@Autowired
	WebClient client;
	
    private String Url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
    
    
    @PostMapping
    public Mono<ResponseEntity<String>> getAuthorization(@RequestBody String login) {
        String Url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
       try {
    	    client = WebClient.create();
    	    return client.post().uri(Url)
        	.accept(MediaType.APPLICATION_JSON)
        	.bodyValue(login).retrieve().toEntity(String.class);
	}  catch (WebClientResponseException e) {
        	System.err.println("Error Response: " + e.getStatusText());
        	System.err.println("Response Body: " + e.getResponseBodyAsString());
        	e.getResponseBodyAsString();
        	return null;
    } 
		
           
    }
    
    @GetMapping
    public Mono<ResponseEntity<String>> list_Of_Customer(@RequestHeader ("Authorization") String bearerToken,@RequestParam String cmd) {
    	 client = WebClient.builder()
                 .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                 .build();
    	 try {
    		 
    		 URI uri = UriComponentsBuilder.fromUriString(Url)
    		            .queryParam("cmd", cmd)
    		            .build()
    		            .toUri();
      	
      	   return client.get().uri(uri)
           .accept(MediaType.APPLICATION_JSON).retrieve().toEntity(String.class);
  	}  
    	 catch (WebClientResponseException e) {
          // Log the details for debugging
          	System.err.println("Error Response: " + e.getStatusText());
          	System.err.println("Response Body: " + e.getResponseBodyAsString());
         	 e.getResponseBodyAsString();
          	return null;
      } 
	}
    
 


    @PostMapping("/create")
    public Mono<ResponseEntity<String>> createCustomer(@RequestHeader ("Authorization") String bearerToken,@RequestParam String cmd,@RequestBody String body) {
		client = WebClient.builder()
				.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
				.build();
		try {
			URI uri = UriComponentsBuilder.fromUriString(Url)
	            .queryParam("cmd", cmd)
	            .build()
	            .toUri();
		
		return client.post().uri(uri)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(body)
				.retrieve()
				.toEntity(String.class);
	}  
		catch (WebClientResponseException e) {
		// Log the details for debugging
			System.err.println("Error Response: " + e.getStatusText());
			System.err.println("Response Body: " + e.getResponseBodyAsString());
			e.getResponseBodyAsString();
		return null;
	} 
}
	@PostMapping("/delete")
	public Mono<ResponseEntity<String>> deleteCustomer(@RequestHeader ("Authorization") String bearerToken,@RequestParam String cmd,@RequestParam String uuid) {
	
           
	
		client = WebClient.builder()
				.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
				.build();
		try {
		
			URI uri = UriComponentsBuilder.fromUriString(Url)
	            .queryParam("cmd", cmd)
	            .queryParam("uuid", uuid)
	            .build()
	            .toUri();

				return client.post()
	            .uri(uri)
	            .accept(MediaType.APPLICATION_JSON)
	            .retrieve()
	            .toEntity(String.class);
	}  
		catch (WebClientResponseException e) {
		// Log the details for debugging
			System.err.println("Error Response: " + e.getStatusText());
			System.err.println("Response Body: " + e.getResponseBodyAsString());
			e.getResponseBodyAsString();
			return null;
	} 
}


}
