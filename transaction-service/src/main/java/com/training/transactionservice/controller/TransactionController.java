package com.training.transactionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.transactionservice.model.Transaction;
import com.training.transactionservice.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@PostMapping("/api")
	public Transaction createTransaction(@RequestParam String accountNumber,
										 @RequestParam Double amount,
										 @RequestParam String type) {
		return service.createTransaction(accountNumber, amount, type);
	}
	
	@GetMapping
	public List<Transaction> getAllTransactions(@RequestParam String accountNumber){
		return service.getAllTransactions(accountNumber);
	}

}
