package com.gryphon.banking.domain.account;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.money.MonetaryAmount;

import com.gryphon.banking.domain.money.Money;

public class Account {

    public static final String DEFAULT_NAME = "Untitled";
    private String id;
    private String name;
    private List<Transaction> transactions;
    
    public Account(String id, String name, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.transactions = Objects.requireNonNull(transactions);
    }
    
    public Account(String id, String name) {
        this(id, name, new ArrayList<>());
    }
    
    public Account() {
        this(null, DEFAULT_NAME);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
    
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    
    public MonetaryAmount getAmount() {
        return transactions
            .stream()
            .map(Transaction::getAmount)
            .reduce(Money.dollars(0), MonetaryAmount::add);
    }
}
