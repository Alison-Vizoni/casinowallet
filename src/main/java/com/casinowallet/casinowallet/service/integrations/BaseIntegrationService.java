package com.casinowallet.casinowallet.service.integrations;

import com.casinowallet.casinowallet.models.dto.integrations.BaseBalanceDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseBetDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseRollbackDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseWinDto;
import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.security.PlayerJwtUtil;
import com.casinowallet.casinowallet.service.core.AccessService;
import com.casinowallet.casinowallet.service.core.PlayerService;
import com.casinowallet.casinowallet.service.core.TransactionService;
import com.casinowallet.casinowallet.service.core.WalletService;
import com.casinowallet.casinowallet.service.exceptions.InvalidAccessException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BaseIntegrationService {
    @Autowired
    protected TransactionService transactionService;

    @Autowired
    protected PlayerService playerService;

    @Autowired
    protected WalletService walletService;

    @Autowired
    private AccessService accessService;

    protected Access access;

    public abstract BaseBalanceDto balance();

    public abstract BaseBetDto bet(Transaction transaction);

    public abstract BaseRollbackDto rollback(Transaction transaction);

    public abstract BaseWinDto win(Transaction transaction);

    public void validateSignature(String signature) {

    }

    public void validatePlayerToken(String token) {
        Claims claims = PlayerJwtUtil.getClaims(token);
        if (claims == null || claims.isEmpty()) {
            throw new InvalidAccessException("Claims is null or empty.");
        }

        Long accessId = (Long) claims.get("access");
        this.access = accessService.findById(accessId);

        accessService.validateAccess(this.access);
    }

    public String getLockKey() {
        return new StringBuilder()
                .append(access.getPlayer().getId())
                .append(access.getGame().getId())
                .append(access.getCurrencyCode()).toString();
    }
}
