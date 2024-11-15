package com.training.gatewayserver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account-details")
public class AggregationController {
	
	private final WebClient.Builder webClientBuilder;
	
	@Autowired
	public AggregationController(WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
	}
	
	@GetMapping("/{accountNumber}")
	public Mono<Map<String, Object>>getAccountDetailsWithTransaction(@PathVariable String accountNumber) {
		
		//Call account-service
		Mono<Map> accountMonoMap = webClientBuilder.build()
									.get()
									.uri("http://localhost:8081/accounts/"+accountNumber)
									.retrieve()
									.bodyToMono(Map.class);
		
		//call the transaction-service
		Mono<Map[]> transactionMonoMap = webClientBuilder.build()
										.get()
										.uri("http://localhost:8082/transaction?accountNumber="+accountNumber)
										.retrieve()
										.bodyToMono(Map[].class);
		
		return Mono.zip(accountMonoMap, transactionMonoMap)
				.map(t->{
						Map<String, Object> aggregate = new HashMap<>();
						aggregate.put("account", t.getT1());
						aggregate.put("transactions", t.getT2());
						return aggregate;
				});
	}

}
