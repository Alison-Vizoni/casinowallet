package com.casinowallet.casinowallet.service.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.direct.*;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.models.entity.Wallet;
import com.casinowallet.casinowallet.service.integrations.BaseIntegrationService;
import org.springframework.stereotype.Service;

@Service
public class DirectIntegrationService extends BaseIntegrationService {

    @Override
    public BalanceDto balance() {
        Wallet wallet = access.getPlayer().getWallet();
        return new BalanceDto(wallet);
    }

    @Override
    public BetDto bet(Transaction transaction) {
        return null;
    }

    @Override
    public RollbackDto rollback(Transaction transaction) {
        return null;
    }

    @Override
    public WinDto win(Transaction transaction) {
        return null;
    }

    public Transaction fromDto(BetNewDto betNewDto) {
        return new Transaction();
    }

    public Transaction fromDto(WinNewDto betNewDto) {
        return new Transaction();
    }
}
