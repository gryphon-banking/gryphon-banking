package com.gryphon.banking.domain.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.money.MonetaryAmount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gryphon.banking.domain.account.Transaction;
import com.gryphon.banking.domain.money.Money;

public class TransactionTest {
    
    private static final String ID = "3bbba7a8-1b61-11ea-978f-2e728ce88125";
    private static final MonetaryAmount AMOUNT = Money.dollars(1);
    private static final Instant INSTANT = Instant.EPOCH;
    private Transaction transaction;
    
    @BeforeEach
    public void setUp() {
        transaction = new Transaction(ID, AMOUNT, INSTANT);
    }
    
    @Test
    public void whenConstructingTransactionWithoutArguments_ThenFieldsPopulated() {
        
        Transaction emptyTransaction = new Transaction();
        
        assertNull(emptyTransaction.getId());
        assertEquals(Money.dollars(0), emptyTransaction.getAmount());
        assertDateWithinOneSecond(emptyTransaction);
    }

    private static void assertDateWithinOneSecond(Transaction emptyTransaction) {
        assertTrue(Instant.now().minus(1, ChronoUnit.SECONDS).isBefore(emptyTransaction.getDate()));
    }

    @Test
    public void givenDifferentObject_WhenEquals_ThenFalse() {
        assertFalse(transaction.equals(new Object()));
    }

    @Test
    public void givenSameObject_WhenEquals_ThenTrue() {
        assertTrue(transaction.equals(transaction));
    }

    @Test
    public void givenAnotherTransactionWithSameId_WhenEquals_ThenTrue() {
        assertTrue(transaction.equals(transactionWithId(ID)));
    }

    @Test
    public void givenAnotherTransactionWithSameIdButDifferentFields_WhenEquals_ThenTrue() {
        assertTrue(transaction.equals(new Transaction(ID, Money.dollars(100), Instant.MAX)));
    }

    private Transaction transactionWithId(String id) {
        return new Transaction(id, AMOUNT, INSTANT);
    }

    @Test
    public void givenAnotherTransactionWithDifferentId_WhenEquals_ThenFalse() {
        assertFalse(transaction.equals(transactionWithId(ID + "diff")));
    }

    @Test
    public void givenAnotherTransactionWithNullId_WhenEquals_ThenFalse() {
        assertFalse(transaction.equals(transactionWithId(null)));
    }
    
    @Test
    public void givenDifferentObject_WhenHashCode_ThenNotEquals() {
        assertNotEquals(new Object().hashCode(), transaction.hashCode());
    }

    @Test
    public void givenSameObject_WhenHashCode_ThenEquals() {
        assertEquals(transaction.hashCode(), transaction.hashCode());
    }

    @Test
    public void givenAnotherTransactionWithSameId_WhenHashCode_ThenEquals() {
        assertEquals(transactionWithId(ID).hashCode(), transaction.hashCode());
    }

    @Test
    public void givenAnotherTransactionWithSameIdButDifferentFields_WhenHashCode_ThenEquals() {
        assertEquals(new Transaction(ID, Money.dollars(100), Instant.MAX).hashCode(), transaction.hashCode());
    }

    @Test
    public void givenAnotherTransactionWithDifferentId_WhenHashCode_ThenNotEquals() {
        assertNotEquals(transactionWithId(ID + "diff").hashCode(), transaction.hashCode());
    }

    @Test
    public void givenAnotherTransactionWithNullId_WhenHashCode_ThenNotEquals() {
        assertNotEquals(transactionWithId(null).hashCode(), transaction.hashCode());
    }
}
