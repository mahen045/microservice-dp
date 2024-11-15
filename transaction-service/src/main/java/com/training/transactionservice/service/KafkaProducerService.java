package com.training.transactionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
	
	private static final String TOPIC = "transaction-topic";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendTransactionMessage(String message) {
		kafkaTemplate.send(TOPIC, message);
	}

}
