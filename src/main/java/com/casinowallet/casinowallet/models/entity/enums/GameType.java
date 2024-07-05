package com.casinowallet.casinowallet.models.entity.enums;

import lombok.Getter;

@Getter
public enum GameType {
    SLOT(1L, "Slot"),
    BINGO(2L, "Bingo");

    private final Long code;
    private final String type;

    GameType(Long code, String type) {
        this.code = code;
        this.type = type;
    }

    public static GameType toEnum(Long code) {
        if (null == code) {
            throw new IllegalArgumentException("Game code can't be null.");
        }

        for(GameType gameType: GameType.values()){
            if(code.equals(gameType.getCode())){
                return gameType;
            }
        }
        throw new IllegalArgumentException("Invalid game code: " + code);
    }

    public static GameType toEnum(String type) {
        if (null == type) {
            throw new IllegalArgumentException("Game type can't be null.");
        }

        for(GameType gameType: GameType.values()){
            if(type.equals(gameType.getType())){
                return gameType;
            }
        }
        throw new IllegalArgumentException("Invalid game name: " + type);
    }
}
