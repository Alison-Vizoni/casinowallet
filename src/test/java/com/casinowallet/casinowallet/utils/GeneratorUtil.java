package com.casinowallet.casinowallet.utils;

import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.models.entity.enums.CurrencyType;
import java.util.Random;

public class GeneratorUtil {

    private static final Random random = new Random();;
    private static Long currencyId = 1L;
    private static final String[] CURRENCY_CODES = {
            "EUR", "USD", "BRL"
    };
    private static final CurrencyType[] CURRENCY_TYPES = {
            CurrencyType.FIAT, CurrencyType.CRYPTO
    };

    public static Currency generateCurrency(){
        Currency currency = new Currency();
        currency.setId(currencyId);
        currency.setCode(CURRENCY_CODES[random.nextInt(CURRENCY_CODES.length)]);
        currency.setRate(random.nextDouble() * 10);
        currency.setType(CURRENCY_TYPES[random.nextInt(CURRENCY_TYPES.length)]);
        currency.setEnabled(true);

        currencyId++;
        return currency;
    }
}
