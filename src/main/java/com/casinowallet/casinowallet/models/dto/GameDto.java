package com.casinowallet.casinowallet.models.dto;

import com.casinowallet.casinowallet.models.entity.Game;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class GameDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "StrId is required.")
    @Length(min = 1, max = 50, message = "StrId length must be between 1 and 50.")
    private String strId;

    @NotEmpty(message = "Type is required.")
    private String type;

    @NotNull(message = "ProviderId is required.")
    private Long providerId;

    public GameDto(Game game) {
        this.strId = game.getStrId();
        this.type = game.getType().getType();
        this.providerId = game.getProvider().getId();
    }
}
