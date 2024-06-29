package com.casinowallet.casinowallet.models.entity.enums;

import lombok.Getter;

@Getter
public enum CurrencyType {
    FIAT(1L, "Fiat"),
    CRYPTO(2L, "Crypto");

    private final Long code;
    private final String type;

    CurrencyType(Long code, String type) {
        this.code = code;
        this.type = type;
    }

    public static CurrencyType toEnum(Long code) {
        if (null == code) {
            throw new IllegalArgumentException("Currency code can't be null.");
        }

        for(CurrencyType currencyType: CurrencyType.values()){
            if(code.equals(currencyType.getCode())){
                return currencyType;
            }
        }
        throw new IllegalArgumentException("Invalid currency code: " + code);
    }

    public static CurrencyType toEnum(String type) {
        if (null == type) {
            throw new IllegalArgumentException("Currency type can't be null.");
        }

        for(CurrencyType currencyType: CurrencyType.values()){
            if(type.equals(currencyType.getType())){
                return currencyType;
            }
        }
        throw new IllegalArgumentException("Invalid currency name: " + type);
    }
}
