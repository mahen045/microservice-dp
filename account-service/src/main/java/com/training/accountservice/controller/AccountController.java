package com.training.accountservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.accountservice.model.Account;
import com.training.accountservice.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
	@GetMapping("/allAccounts")
	public List<Account> getAccounts() {
		return service.getAccounts();
	}
	
	@PostMapping("/addAccount")
	public Account addAccount(@RequestBody Account account) {
		return service.addAccount(account);
	}
	
	@GetMapping("/{accountNumber}")
	public Account getByNumber(@PathVariable String accountNumber) {
		return service.getByAccountNumber(accountNumber);
	}
	
	@PutMapping("/{accountNumber}/balance")
	public Account updateBalance(@PathVariable String accountNumber, @RequestBody Double amount) {
		return service.updateBalance(accountNumber, amount);
	}

}
