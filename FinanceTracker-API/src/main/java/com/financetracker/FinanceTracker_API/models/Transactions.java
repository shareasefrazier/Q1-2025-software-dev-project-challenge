package com.financetracker.FinanceTracker_API.models;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Transactions extends AbstractEntity{

    private String description;
    private BigDecimal amount;


    //Constructors

    public Transactions() {}

    public Transactions(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

    //Getters and Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
