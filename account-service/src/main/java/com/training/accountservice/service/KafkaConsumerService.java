package com.training.accountservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	
	@Autowired
	private AccountService accountService;
	
	@KafkaListener(topics = "transaction-topic", groupId="account-service-group")
	public void consumeTransactionMessage(String message) {
		
		String[] parts = message.split(" ");
		String type = parts[2];
		double amount = Double.parseDouble(parts[3]);
		String accountNumber = parts[6];
		accountService.updateBalance(accountNumber, type.equalsIgnoreCase("debit")?-amount : amount);
	}

}
