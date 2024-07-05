package com.casinowallet.casinowallet.models.dto;

import com.casinowallet.casinowallet.models.entity.Game;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class GameDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String strId;

    private String type;

    public GameDto(Game game) {
        this.strId = game.getStrId();
        this.type = game.getType().getType();
    }
}
