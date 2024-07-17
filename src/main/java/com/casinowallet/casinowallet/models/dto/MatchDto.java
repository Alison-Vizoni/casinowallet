package com.casinowallet.casinowallet.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class MatchDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "")
    private String externalId;

    @NotNull(message = "")
    private Boolean ended;

    @NotNull(message = "")
    private Long gameId;

    @NotNull(message = "")
    private Long accessId;
}
