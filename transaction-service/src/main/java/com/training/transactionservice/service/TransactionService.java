package com.training.transactionservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.transactionservice.intercomm.AccountClient;
import com.training.transactionservice.model.Transaction;
import com.training.transactionservice.repository.TransactionRepository;

@Service
public class TransactionService {
	
	private static final String ACCOUNT_SERVICE_CB = "accountServiceCircuitBreaker";
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private AccountClient accountClient;
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	
	
	/*
	 * @CircuitBreaker(name = ACCOUNT_SERVICE_CB, fallbackMethod =
	 * "fallbackCreateTransaction") public Transaction createTransaction(String
	 * accountNumber, Double amount, String type) {
	 * 
	 * Account account = accountClient.getAccount(accountNumber);
	 * 
	 * if(type.equalsIgnoreCase("debit") && account.getBalance() < amount) { throw
	 * new RuntimeException("Insufficient balance"); }
	 * 
	 * Transaction transaction = new Transaction();
	 * transaction.setAccountNumber(accountNumber); transaction.setAmount(amount);
	 * transaction.setType(type);
	 * 
	 * accountClient.updateBalance(accountNumber, type.equalsIgnoreCase("debit")?
	 * -amount : amount); return repository.save(transaction); }
	 */
	
	public List<Transaction> getAllTransactions(String accountNumber){
		return repository.findByAccountNumber(accountNumber)
				.orElseThrow(()->new RuntimeException("Transaction not found"));
	}
	
	/*
	 * public Transaction fallbackCreateTransaction(String accountNumber, Double
	 * amount, String type, Throwable t) {
	 * 
	 * System.out.println("Within Circut-Breaker: "+t.getMessage()); Transaction
	 * transaction = new Transaction(); transaction.setAccountNumber(accountNumber);
	 * transaction.setAmount(0.0); transaction.setType("FAILED"); return
	 * repository.save(transaction); }
	 */
	
	public Transaction createTransaction(String accountNumber, Double amount, String type) {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		transaction.setType(type);
		
		repository.save(transaction);
		
		String message = String.format("Transaction of %s %f for account %s", type, amount, accountNumber);
		kafkaProducerService.sendTransactionMessage(message);
		return transaction;
	}

}
