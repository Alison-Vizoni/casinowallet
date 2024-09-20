package com.casinowallet.casinowallet.models.dto.integrations.direct;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class BetNewDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "TransactionExternalId is required.")
    private String transactionExternalId;

    @Positive
    @NotNull(message = "Amount is required.")
    private Long amount;

    @NotEmpty(message = "MatchExternalId id required.")
    private String matchExternalId;

    private String referenceBetExternalId;
    private Boolean isFree = false;
    private String requestId;
}
