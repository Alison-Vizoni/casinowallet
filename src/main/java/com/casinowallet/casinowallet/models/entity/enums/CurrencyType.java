package com.casinowallet.casinowallet.models.entity.enums;

import lombok.Getter;

@Getter
public enum CurrencyType {
    FIAT(1L, "Fiat"),
    CRYPTO(2L, "Crypto");

    private final Long code;
    private final String description;

    CurrencyType(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CurrencyType toEnum(Long code) {
        for(CurrencyType currencyType: CurrencyType.values()){
            if(code.equals(currencyType.getCode())){
                return currencyType;
            }
        }
        throw new IllegalArgumentException("Invalid currency code: " + code);
    }
}
