package com.gryphon.banking.domain.money;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.money.MonetaryAmount;

import org.junit.jupiter.api.Test;

import com.gryphon.banking.domain.money.Money;

public class MoneyTest {

    @Test
    public void givenNegativeWholeAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(-1);
        
        assertIsInDollars(amount);
        assertEqualsAsLong(-1, amount);
        assertEqualsAsDouble(-1.0, amount);
    }

    private static void assertIsInDollars(MonetaryAmount amount) {
        assertEquals("USD", amount.getCurrency().getCurrencyCode());
    }

    private static void assertEqualsAsLong(long expected, MonetaryAmount amount) {
        assertEquals(expected, amount.getNumber().longValue());
    }

    private static void assertEqualsAsDouble(double expected, MonetaryAmount amount) {
        assertEquals(expected, amount.getNumber().doubleValue());
    }

    @Test
    public void givenNegativeDecimalAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(-1.1);
        
        assertIsInDollars(amount);
        assertEqualsAsDouble(-1.1, amount);
    }

    @Test
    public void givenZeroWholeAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(0);
        
        assertIsInDollars(amount);
        assertEqualsAsLong(0, amount);
        assertEqualsAsDouble(0.0, amount);
    }

    @Test
    public void givenPositiveWholeAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(1);
        
        assertIsInDollars(amount);
        assertEqualsAsLong(1, amount);
        assertEqualsAsDouble(1.0, amount);
    }

    @Test
    public void givenPositiveDecimalAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(1.1);
        
        assertIsInDollars(amount);
        assertEqualsAsDouble(1.1, amount);
    }

    @Test
    public void givenLowestNegativeWholeAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(Long.MIN_VALUE);
        
        assertIsInDollars(amount);
        assertEqualsAsLong(Long.MIN_VALUE, amount);
        assertEqualsAsDouble(Long.MIN_VALUE, amount);
    }

    @Test
    public void givenHighestPositiveWholeAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(Long.MAX_VALUE);
        
        assertIsInDollars(amount);
        assertEqualsAsLong(Long.MAX_VALUE, amount);
        assertEqualsAsDouble(Long.MAX_VALUE, amount);
    }

    @Test
    public void givenLowestNegativeDecimalAmount_WhenDollars_ThenDollarValueRollsOver() {
        
        MonetaryAmount amount = Money.dollars(Double.MIN_VALUE);
        
        assertIsInDollars(amount);
        assertEqualsAsDouble(0.0, amount);
    }

    @Test
    public void givenHighestPositiveDecimalAmount_WhenDollars_ThenDollarValueIsCorrect() {
        
        MonetaryAmount amount = Money.dollars(Double.MAX_VALUE);
        
        assertIsInDollars(amount);
        assertEqualsAsDouble(Double.MAX_VALUE, amount);
    }
}
