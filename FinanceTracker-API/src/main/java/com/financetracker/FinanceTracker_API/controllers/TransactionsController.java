package com.financetracker.FinanceTracker_API.controllers;

import com.financetracker.FinanceTracker_API.models.Transactions;
import com.financetracker.FinanceTracker_API.models.User;
import com.financetracker.FinanceTracker_API.service.TransactionsService;
import com.financetracker.FinanceTracker_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transactions>> getTransactionsByUser(@PathVariable("userId") Long userId) {
        List<Transactions> transactions = transactionsService.getTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/addTransactions")
    public String showAddTransactionsPage(@RequestParam("userId") Long userId, Model model) {
        Transactions transactions = new Transactions();
        model.addAttribute("transactions", transactions);
        model.addAttribute("userId", userId);
        return "add-transactions";
    }

    @PostMapping("/saveTransactions")
    public String saveTransactions(@RequestBody Transactions transactions) {
        User user = userService.getUserById(transactions.getUser().getId());
        transactions.setUser(user);
        transactionsService.saveTransactions(transactions);
        return "redirect:/transactions/user/" + transactions.getUser().getId();
    }

    @GetMapping("/editTransactions/{id}")
    public String showUpdatedTransactionsPage(@PathVariable("id") Long transactionId, Model model) {
        Transactions transactions = transactionsService.getTransactionById(transactionId);
        model.addAttribute("transactions", transactions);
        return "update-transactions";
    }

    @PostMapping("/updateTransactions/{id}")
    public String updateTransaction(@PathVariable("id") Long transactionsId, @ModelAttribute("transactions") Transactions updatedTransaction) {
        Transactions existingTransactions = transactionsService.getTransactionById(transactionsId);
        existingTransactions.setDescription(updatedTransaction.getDescription());
        existingTransactions.setAmount(updatedTransaction.getAmount());
        transactionsService.saveTransactions(existingTransactions);
        return "redirect:/transactions/user" + existingTransactions.getUser().getId();
    }

    @GetMapping("deleteTransactions/{id}")
    public String deleteTransactions(@PathVariable("id") Long transactionsId) {
        transactionsService.deleteTransactionById(transactionsId);
        return "redirect:/";
    }
}
