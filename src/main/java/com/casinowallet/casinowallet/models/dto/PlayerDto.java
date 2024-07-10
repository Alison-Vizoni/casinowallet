package com.casinowallet.casinowallet.models.dto;

import com.casinowallet.casinowallet.models.entity.Player;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PlayerDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Nickname is required.")
    @Length(min = 5, max = 40, message = "Nickname length must be between 5 and 40.")
    private String nickname;

    public PlayerDto(Player player){
        this.nickname = player.getNickname();
    }
}
