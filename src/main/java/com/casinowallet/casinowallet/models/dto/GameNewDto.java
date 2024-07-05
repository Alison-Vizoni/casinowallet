package com.casinowallet.casinowallet.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Getter
public class GameNewDto  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "StrId is required.")
    @Length(min = 1, max = 50, message = "StrId length must be between 1 and 50.")
    private String strId;

    @NotEmpty(message = "Type is required.")
    private String type;

    @NotNull(message = "ProviderId is required.")
    private Long providerId;
}
