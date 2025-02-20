package com.financetracker.FinanceTracker_API.repositories;

import com.financetracker.FinanceTracker_API.models.Transactions;
import com.financetracker.FinanceTracker_API.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
        List<Transactions> findByUser(User user);
        List<Transactions> findByUserId(Long userId);
}
