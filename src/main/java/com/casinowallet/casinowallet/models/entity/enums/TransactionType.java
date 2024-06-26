package com.casinowallet.casinowallet.models.entity.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    BET(1L, "Bet"),
    WIN(2L, "Win"),
    ROLLBACK(3L, "Rollback");

    private final Long code;
    private final String name;

    TransactionType(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public static TransactionType toEnum(Long code) {
        for(TransactionType transactionType: TransactionType.values()){
            if(code.equals(transactionType.getCode())){
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid transaction code: " + code);
    }

    public static TransactionType toEnum(String name) {
        for(TransactionType transactionType: TransactionType.values()){
            if(name.equals(transactionType.getName())){
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid transaction name: " + name);
    }
}
