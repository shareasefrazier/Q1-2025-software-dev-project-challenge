package com.financetracker.FinanceTracker_API.controllers;

import com.financetracker.FinanceTracker_API.models.Transactions;
import com.financetracker.FinanceTracker_API.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Transactions> transactions = transactionsService.getAllTransactions();
        BigDecimal totalAmount = transactions.stream().map(Transactions::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        model.addAttribute("transaction", transactions);
        model.addAttribute("totalAmount", totalAmount);
        return "index";
    }

    @GetMapping("/addTransactions")
    public String showAddTransactionsPage(Model model) {
        Transactions transactions = new Transactions();
        model.addAttribute("transactions", transactions);
        return "add-transactions";
    }

    @PostMapping("/saveTransactions")
    public String saveTransactions(@ModelAttribute("transactions") Transactions transactions, Model model) {
        transactionsService.saveTransactions(transactions);
        return "redirect:/" ;
    }

    @GetMapping("/editTransactions/{id}")
    public String showUpdatedTransactionsPage(@PathVariable("id") long id, Model model) {
        Transactions transactions = transactionsService.getTransactionById(id);
        model.addAttribute("transactions", transactions);
        return "update-transactions";
    }

    @PostMapping("/updateTransactions/{id}")
    public String updateTransaction(@PathVariable("id") long id, @ModelAttribute("transactions") Transactions transactions) {
        Transactions existingTransactions = transactionsService.getTransactionById(id);
        existingTransactions.setDescription(transactions.getDescription());
        existingTransactions.setAmount(transactions.getAmount());
        transactionsService.saveTransactions(existingTransactions);
        return "redirect:/";
    }

    @GetMapping("deleteTransactions/{id}")
    public String deleteTransactions(@PathVariable("id") long id) {
        transactionsService.deleteTransactionById(id);
        return "redirect:/";
    }
}
