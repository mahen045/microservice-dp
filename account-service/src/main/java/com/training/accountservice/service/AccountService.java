package com.training.accountservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.accountservice.model.Account;
import com.training.accountservice.repository.AccountServiceRepository;

@Service
public class AccountService {

	@Autowired
	private AccountServiceRepository repository;
	
	public Account addAccount(Account account) {
		return repository.save(account);
		
	}
	
	public List<Account> getAccounts(){
		return repository.findAll();
	}
	
	public Account getByAccountNumber(String accountNumber) {
		return repository.findByAccountNumber(accountNumber)
		.orElseThrow(() -> new RuntimeException("Account not found"));	
	}
	
	public Account updateBalance(String accountNumber, Double amount) {
		Account account = getByAccountNumber(accountNumber);
		account.setBalance(account.getBalance() + amount);
		return repository.save(account);
	}
}
