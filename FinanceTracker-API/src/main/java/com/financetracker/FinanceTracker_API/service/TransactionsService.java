package com.financetracker.FinanceTracker_API.service;

import com.financetracker.FinanceTracker_API.models.Transactions;
import com.financetracker.FinanceTracker_API.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    public List<Transactions> getTransactionsByUserId(Long userId) {
        return transactionsRepository.findByUserId(userId);
    }

    public void saveTransactions(Transactions transactions) {
        transactionsRepository.save(transactions);
    }

    public Transactions getTransactionById(Long id) {
        return transactionsRepository.findById(id).orElse(null);
    }

    public void deleteTransactionById(Long id) {
        transactionsRepository.deleteById(id);
    }
}
