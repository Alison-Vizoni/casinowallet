package com.casinowallet.casinowallet.service.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.direct.*;
import com.casinowallet.casinowallet.models.entity.Match;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.models.entity.Wallet;
import com.casinowallet.casinowallet.models.entity.enums.TransactionType;
import com.casinowallet.casinowallet.service.integrations.BaseIntegrationService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DirectIntegrationService extends BaseIntegrationService {

    @Override
    public BalanceDto balance() {
        Wallet wallet = access.getPlayer().getWallet();
        return new BalanceDto(wallet);
    }

    @Override
    public BetDto bet(Transaction transaction) {
        Transaction dbTxn = findTransaction(transaction);
        if (dbTxn != null) {
            return new BetDto(validateExistentBetTransaction(dbTxn, transaction));
        }

        playerHasEnoughMoney(transaction);
        manageTransactionMatch(transaction);
        insertTransaction(transaction);
        decrement(transaction.getAmount());
        return new BetDto(transaction);
    }

    @Override
    public RollbackDto rollback(Transaction transaction) {
        return null;
    }

    @Override
    public WinDto win(Transaction transaction) {
        return null;
    }

    public Transaction fromDto(BetNewDto betNewDto, TransactionType transactionType) {
        Match match = new Match(
                null,
                betNewDto.getMatchExternalId(),
                null,
                null,
                null,
                null
        );
        return new Transaction(
                null,
                betNewDto.getTransactionExternalId(),
                transactionType,
                betNewDto.getAmount(),
                betNewDto.getIsFree(),
                Instant.now(),
                this.access.getCurrencyCode(),
                this.access.getGame().getId(),
                this.access.getGame().getProvider(),
                this.access.getPlayer().getWallet(),
                match
        );
    }

    public Transaction fromDto(WinNewDto winNewDto) {
        return new Transaction();
    }
}
