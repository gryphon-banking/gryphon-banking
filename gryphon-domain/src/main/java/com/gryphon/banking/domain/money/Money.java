package com.gryphon.banking.domain.money;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.MonetaryAmountFactory;

public class Money {

    private static final MonetaryAmountFactory<?> USD_CURRENCY_FACTORY = Monetary.getDefaultAmountFactory()
        .setCurrency("USD");

    public static MonetaryAmount dollars(long amount) {
        return USD_CURRENCY_FACTORY
            .setNumber(amount)
            .create();
    }

    public static MonetaryAmount dollars(double amount) {
        return USD_CURRENCY_FACTORY
            .setNumber(amount)
            .create();
    }
}
