package com.gryphon.banking.domain.account;

import java.time.Instant;
import java.util.Objects;

import javax.money.MonetaryAmount;

import com.gryphon.banking.domain.money.Money;

public class Transaction {

    private String id;
    private MonetaryAmount amount;
    private Instant date;
    
    public Transaction(String id, MonetaryAmount amount, Instant date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }
    
    public Transaction() {
        this(null, Money.dollars(0), Instant.now());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof Transaction)) {
            return false;
        }
        else {
            Transaction other = (Transaction) obj;
            return Objects.equals(id, other.id);
        }
    }
}
