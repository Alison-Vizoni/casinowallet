package com.casinowallet.casinowallet.models.dto.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.BaseBetDto;
import com.casinowallet.casinowallet.models.entity.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BetDto extends BaseBetDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String transactionExternalId;
    private Boolean isFree;
    private Long balance;
    private String requestId;

    public BetDto(Transaction transaction) {
        this.transactionExternalId = transaction.getExternalId();
        this.isFree = transaction.getIsFree();
        this.balance = transaction.getWallet().getBalance();
    }
}
