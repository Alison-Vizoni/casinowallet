package com.casinowallet.casinowallet.models.entity.enums;

import lombok.Getter;

@Getter
public enum TransactionType {
    BET(1L, "Bet"),
    WIN(2L, "Win"),
    ROLLBACK(3L, "Rollback");

    private final Long code;
    private final String description;

    TransactionType(Long code, String description) {
        this.code = code;
        this.description = description;
    }

    public static TransactionType toEnum(Long code) {
        for(TransactionType transactionType: TransactionType.values()){
            if(code.equals(transactionType.getCode())){
                return transactionType;
            }
        }
        throw new IllegalArgumentException("Invalid transaction code: " + code);
    }
}
