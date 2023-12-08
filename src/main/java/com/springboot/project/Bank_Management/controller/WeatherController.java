package com.springboot.project.Bank_Management.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
public class WeatherController 
{
	@Autowired
	WebClient client;
	
    private String Url = "http://api.weatherstack.com/current";
    
    @PostMapping
    public Mono<ResponseEntity<String>> getAuthorization(@RequestParam String city) {
        
       try {
    	   URI uri = UriComponentsBuilder.fromUriString(Url)
		            .queryParam("access_key", "c42aaebc01f487d5907c15fbac026b8c")
		            .queryParam("query", city)
		            .build()
		            .toUri();
    	    client = WebClient.create();
    	    return client.post().uri(uri)
        	.accept(MediaType.APPLICATION_JSON)
        	.retrieve().toEntity(String.class);
	}  catch (WebClientResponseException e) {
        	System.err.println("Error Response: " + e.getStatusText());
        	System.err.println("Response Body: " + e.getResponseBodyAsString());
        	e.getResponseBodyAsString();
        	return null;
    } 
		
           
    }

}
