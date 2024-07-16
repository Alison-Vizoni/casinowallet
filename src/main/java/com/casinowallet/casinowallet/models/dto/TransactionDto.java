package com.casinowallet.casinowallet.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@NoArgsConstructor
public class TransactionDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String externalId;
    private String type;
    private Long amount;
    private Boolean isFree;
    private Instant dateTime;
    private String token;
}
