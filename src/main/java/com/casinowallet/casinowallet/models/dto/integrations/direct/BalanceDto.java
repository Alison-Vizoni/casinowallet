package com.casinowallet.casinowallet.models.dto.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.BaseBalanceDto;
import com.casinowallet.casinowallet.models.entity.Wallet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BalanceDto extends BaseBalanceDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long balance;

    public BalanceDto(Wallet wallet){
        this.balance = wallet.getBalance();
    }
}
