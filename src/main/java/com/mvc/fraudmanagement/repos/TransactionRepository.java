package com.mvc.fraudmanagement.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mvc.fraudmanagement.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}