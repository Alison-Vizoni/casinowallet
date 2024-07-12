package com.casinowallet.casinowallet.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class OpenGameDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "GameId is required.")
    private Long gameId;

    @NotNull(message = "PlayerId is required.")
    private Long playerId;

    @NotEmpty(message = "CurrencyCode is required.")
    private String currencyCode;
}
