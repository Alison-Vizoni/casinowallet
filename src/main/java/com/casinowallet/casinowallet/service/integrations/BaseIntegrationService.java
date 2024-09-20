package com.casinowallet.casinowallet.service.integrations;

import com.casinowallet.casinowallet.models.dto.integrations.BaseBalanceDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseBetDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseRollbackDto;
import com.casinowallet.casinowallet.models.dto.integrations.BaseWinDto;
import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.LastMatch;
import com.casinowallet.casinowallet.models.entity.Match;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.models.entity.enums.TransactionType;
import com.casinowallet.casinowallet.security.PlayerJwtUtil;
import com.casinowallet.casinowallet.service.core.*;
import com.casinowallet.casinowallet.service.exceptions.*;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseIntegrationService {
    @Autowired
    protected TransactionService transactionService;

    @Autowired
    protected PlayerService playerService;

    @Autowired
    protected WalletService walletService;

    @Autowired
    protected MatchService matchService;

    @Autowired
    private LastMatchService lastMatchService;

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

    public Transaction findTransaction(Transaction transaction) {
        try {
            return transactionService.findByExternalId(transaction.getProvider().getId(), transaction.getExternalId());
        } catch (DataIntegrityException e) {
            return null;
        }
    }

    public boolean isTransactionEquals(Transaction dbTransaction, Transaction transaction) {
        return dbTransaction.getAmount().equals(transaction.getAmount())
                && dbTransaction.getExternalId().equals(transaction.getExternalId())
                && dbTransaction.getMatch().getExternalId().equals(transaction.getExternalId())
                && dbTransaction.getIsFree().equals(transaction.getIsFree()
                && dbTransaction.getReferenceExternalId().equals(transaction.getReferenceExternalId())
                && dbTransaction.getType().equals(transaction.getType())
        );
    }

    public Transaction validateExistentBetTransaction(Transaction dbTxn, Transaction transaction) {
        if (isTransactionEquals(dbTxn, transaction) && dbTxn.getType().equals(TransactionType.BET)) {
            return dbTxn;
        } else if (dbTxn.getType().equals(TransactionType.ROLLBACK)) {
            throw new TransactionRolledBackException("Transaction was rolled back.");
        } else {
            throw new InvalidTransactionException("Invalid transaction.");
        }
    }

    public Transaction validateExistentWinTransaction(Transaction dbTxn, Transaction transaction) {
        if (isTransactionEquals(dbTxn, transaction) && dbTxn.getType().equals(TransactionType.WIN)) {
            return dbTxn;
        } else {
            throw new InvalidTransactionException("Invalid transaction.");
        }
    }

    public void playerHasEnoughMoney(Transaction transaction) {
        if (this.access.getPlayer().getWallet().getBalance() < transaction.getAmount()){
            throw new NotEnoughMoneyException("The player does not have enough money.");
        }
    }

    public void transactionHasBeenRolledBack(Transaction transaction) {
        Transaction dbTransaction = transactionService.findByReferenceExternalId(transaction.getExternalId());
        if (null != dbTransaction && dbTransaction.getType().equals(TransactionType.ROLLBACK)) {
            throw new TransactionRolledBackException("Transaction has been rolled back.");
        }
    }

    public void decrement(Long amount) {
        walletService.decrement(this.access.getPlayer().getWallet(), amount);
    }

    public void manageTransactionMatch(Transaction transaction) {
        LastMatch lastMatch = lastMatchService.getLastMatch(access);
        Match match = transaction.getMatch();

        if (lastMatch != null) {
            if (lastMatch.getMatch().getExternalId().equals(match.getExternalId())) {
                if (lastMatch.getMatch().getEnded()) {
                    throw new MacthEndedException("The match has ended.");
                }
                transaction.setMatch(lastMatch.getMatch());
            } else {
                matchService.finishMatch(lastMatch.getId());
                Match newMatch = matchService.insert(createMatchObject(match.getExternalId()));
                transaction.setMatch(newMatch);
                lastMatchService.updateLastMatch(lastMatch);
            }
        } else {
            Match newMatch = matchService.insert(createMatchObject(match.getExternalId()));
            transaction.setMatch(newMatch);
            lastMatchService.updateLastMatch(createLastMatchObject(newMatch));
        }
    }

    public void insertTransaction(Transaction transaction) {
        transactionService.insert(transaction);
    }

    private LastMatch createLastMatchObject(Match match) {
        return new LastMatch(
                null,
                access.getGame().getId(),
                access.getCurrencyCode(),
                access.getPlayer().getId(),
                match
        );
    }

    private Match createMatchObject(String externalId) {
        return new Match(
                null,
                externalId,
                false,
                null,
                access.getGame(),
                access
        );
    }
}
