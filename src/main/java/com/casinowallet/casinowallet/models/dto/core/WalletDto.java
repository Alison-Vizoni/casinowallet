package com.casinowallet.casinowallet.models.dto.core;

import com.casinowallet.casinowallet.models.entity.Wallet;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@NoArgsConstructor
public class WalletDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Balance is required.")
    @Positive(message = "Balance should be positive.")
    private Long balance;

    @NotEmpty(message = "BaseCurrency is required.")
    private String baseCurrency;

    @NotNull(message = "PlayerId is required.")
    private Long playerId;

    public WalletDto(Wallet wallet) {
        this.balance = wallet.getBalance();
        this.baseCurrency = wallet.getBaseCurrency();
        this.playerId = wallet.getPlayer().getId();
    }
}
