package com.casinowallet.casinowallet.service.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.direct.BalanceDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.BetDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.RollbackDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.WinDto;
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
}
