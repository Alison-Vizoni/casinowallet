package com.casinowallet.casinowallet.models.entity.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    BET(1L, "Bet"),
    WIN(2L, "Win"),
    ROLLBACK(3L, "Rollback");

    private final Long code;
    private final String type;

    TransactionType(Long code, String type) {
        this.code = code;
        this.type = type;
    }

    public static TransactionType toEnum(Long code) {
        if (null == code) {
            throw new IllegalArgumentException("Transaction code can't be null.");
        }

        for(TransactionType transactionType: TransactionType.values()){
            if(code.equals(transactionType.getCode())){
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid transaction code: " + code);
    }

    public static TransactionType toEnum(String type) {
        if (null == type) {
            throw new IllegalArgumentException("Transaction type can't be null.");
        }

        for(TransactionType transactionType: TransactionType.values()){
            if(type.equals(transactionType.getType())){
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid transaction name: " + type);
    }
}
