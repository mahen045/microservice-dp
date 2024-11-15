package com.training.accountservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.accountservice.model.Account;

public interface AccountServiceRepository extends JpaRepository<Account, Long>{

	Optional<Account> findByAccountNumber(String accountNumber);
}
