package com.financetracker.FinanceTracker_API.repositories;

import com.financetracker.FinanceTracker_API.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {

}
