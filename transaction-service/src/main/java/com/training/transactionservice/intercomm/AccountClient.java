package com.training.transactionservice.intercomm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.transactionservice.model.Account;

//@FeignClient(name = "account-service", url="http://localhost:8081/accounts")
@FeignClient("account-service/accounts")
public interface AccountClient {
	
	@GetMapping("/{accountNumber}")
	Account getAccount(@PathVariable String accountNumber);
	
	@PutMapping("/{accountNumber}/balance")
	Account updateBalance(@PathVariable String accountNumber, @RequestBody Double amount);
}

//Service Discovery Pattern
