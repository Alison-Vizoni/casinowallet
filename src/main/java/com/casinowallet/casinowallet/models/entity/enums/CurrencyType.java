package com.casinowallet.casinowallet.models.entity.enums;

import lombok.Getter;

@Getter
public enum CurrencyType {
    FIAT(1L, "Fiat"),
    CRYPTO(2L, "Crypto");

    private final Long code;
    private final String name;

    CurrencyType(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CurrencyType toEnum(Long code) {
        for(CurrencyType currencyType: CurrencyType.values()){
            if(code.equals(currencyType.getCode())){
                return currencyType;
            }
        }
        throw new IllegalArgumentException("Invalid currency code: " + code);
    }

    public static CurrencyType toEnum(String name) {
        for(CurrencyType currencyType: CurrencyType.values()){
            if(name.equals(currencyType.getName())){
                return currencyType;
            }
        }
        throw new IllegalArgumentException("Invalid currency name: " + name);
    }
}
