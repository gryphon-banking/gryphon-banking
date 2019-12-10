package com.gryphon.banking.domain.account;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gryphon.banking.domain.account.Account;
import com.gryphon.banking.domain.account.Transaction;
import com.gryphon.banking.domain.money.Money;

public class AccountTest {

    private static final String ID = "ca657440-1b78-11ea-978f-2e728ce88125";
    private static final String NAME = "foo";
    private Account account;
    
    @BeforeEach
    public void setUp() {
        account = new Account(ID, NAME);
    }
    
    @Test
    public void givenNullId_WhenConstructWithList_ThenNoExceptionThrown() {
        assertDoesNotThrow(() -> new Account(null, NAME, Collections.emptyList()));
    }
    
    @Test
    public void givenNullName_WhenConstructWithList_ThenNoExceptionThrown() {
        assertDoesNotThrow(() -> new Account(ID, null, Collections.emptyList()));
    }
    
    @Test
    public void givenNullTransactionList_WhenConstructWithList_ThenNullPointerExceptionThrown() {
        assertThrows(NullPointerException.class, () -> new Account(ID, NAME, null));
    }
    
    @Test
    public void givenNullId_WhenConstruct_ThenNoExceptionThrown() {
        assertDoesNotThrow(() -> new Account(null, NAME));
    }
    
    @Test
    public void givenNullName_WhenConstruct_ThenNoExceptionThrown() {
        assertDoesNotThrow(() -> new Account(ID, null));
    }
    
    @Test
    public void whenConstructWithNoArguments_ThenAccountPopulated() {
        
        Account emptyAccount = new Account();
        
        assertNull(emptyAccount.getId());
        assertEquals(Account.DEFAULT_NAME, emptyAccount.getName());
        assertNotNull(emptyAccount.getTransactions());
        assertTrue(emptyAccount.getTransactions().isEmpty());
    }
    
    @Test
    public void givenNoTransactions_WhenGetAmount_ThenAmountIsZero() {
        assertEquals(Money.dollars(0), account.getAmount());
    }
    
    @Test
    public void givenOneTransactionWithZeroAmount_WhenGetAmount_ThenAmountIsZero() {
        
        account.addTransaction(transactionWithAmount(0));
        
        assertEquals(Money.dollars(0), account.getAmount());
    }

    private static Transaction transactionWithAmount(double amount) {
        return new Transaction("foo", Money.dollars(amount), Instant.EPOCH);
    }
    
    @Test
    public void givenOneTransactionWithPositiveAmount_WhenGetAmount_ThenAmountIsZero() {
        
        account.addTransaction(transactionWithAmount(1));
        
        assertEquals(Money.dollars(1), account.getAmount());
    }
    
    @Test
    public void givenOneTransactionWithNegativeAmount_WhenGetAmount_ThenAmountIsZero() {
        
        account.addTransaction(transactionWithAmount(-1));
        
        assertEquals(Money.dollars(-1), account.getAmount());
    }
    
    @Test
    public void givenTwoTransactionWithPositiveAmount_WhenGetAmount_ThenAmountIsZero() {
        
        account.addTransaction(transactionWithAmount(1));
        account.addTransaction(transactionWithAmount(2));
        
        assertEquals(Money.dollars(3), account.getAmount());
    }
    
    @Test
    public void givenTwoTransactionWithNegativeAmount_WhenGetAmount_ThenAmountIsZero() {
        
        account.addTransaction(transactionWithAmount(-1));
        account.addTransaction(transactionWithAmount(-2));
        
        assertEquals(Money.dollars(-3), account.getAmount());
    }
    
    @Test
    public void givenTwoTransactionWithOneNegativeAmountAndOneNegativeAmount_WhenGetAmount_ThenAmountIsZero() {
        
        account.addTransaction(transactionWithAmount(-1));
        account.addTransaction(transactionWithAmount(2));
        
        assertEquals(Money.dollars(1), account.getAmount());
    }
}
