package com.talentwunder.financetracker.repository;

import com.talentwunder.financetracker.model.db.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
