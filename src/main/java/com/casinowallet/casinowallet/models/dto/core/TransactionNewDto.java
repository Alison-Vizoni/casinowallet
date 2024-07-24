package com.casinowallet.casinowallet.models.dto.core;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class TransactionNewDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "ExternalId is required.")
    private String externalId;

    @NotEmpty(message = "Type is required.")
    private String type;

    @NotNull(message = "Amount is required.")
    private Long amount;

    @NotNull(message = "IsFree can't be null.")
    private Boolean isFree = false;

    @NotNull(message = "DateTime is required.")
    private Instant dateTime;

    @NotEmpty(message = "MatchExternalId is required.")
    private String matchExternalId;
}
