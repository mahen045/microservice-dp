package com.training.transactionservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.transactionservice.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
	Optional<List<Transaction>> findByAccountNumber(String accountNumber);

}
