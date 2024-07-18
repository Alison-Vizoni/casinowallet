package com.casinowallet.casinowallet.service.integrations;

import com.casinowallet.casinowallet.models.dto.integrations.BaseBalanceDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseBetDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseRollbackDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseWinDto;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.service.PlayerService;
import com.casinowallet.casinowallet.service.TransactionService;
import com.casinowallet.casinowallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseIntegrationService {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private WalletService walletService;

    public abstract BaseBalanceDto balance(Transaction transaction);

    public abstract BaseBetDto bet(Transaction transaction);

    public abstract BaseRollbackDto rollback(Transaction transaction);

    public abstract BaseWinDto win(Transaction transaction);
}
