package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.dto.core.TransactionDto;
import com.casinowallet.casinowallet.models.dto.core.TransactionNewDto;
import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.Game;
import com.casinowallet.casinowallet.models.entity.Match;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.models.entity.enums.TransactionType;
import com.casinowallet.casinowallet.repository.TransactionRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private MatchService matchService;

    public Transaction findById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Transaction not found! Id: ")
                .append(id).toString()));
    }

    public Transaction findByExternalId(Long providerId, String externalId) {
        Optional<Transaction> transaction = transactionRepository.findByProviderIdAndExternalId(providerId, externalId);
        return transaction.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Transaction not found! externalId: ")
                .append(externalId).toString()));
    }

    public Transaction insert(Transaction transaction) {
        transaction.setId(null);
        try {
            return transactionRepository.save(transaction);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving the transaction in the database.", e);
        }
    }

    public Transaction fromDto(TransactionNewDto transactionNewDto) {
        Match match = matchService.findByExternalId(transactionNewDto.getGameId(), transactionNewDto.getExternalId());
        Access access = match.getAccess();
        Game game = access.getGame();

        return new Transaction(
                null,
                transactionNewDto.getExternalId(),
                TransactionType.toEnum(transactionNewDto.getType()),
                transactionNewDto.getAmount(),
                transactionNewDto.getIsFree(),
                transactionNewDto.getDateTime(),
                access.getCurrencyCode(),
                game.getId(),
                game.getProvider(),
                access.getPlayer().getWallet(),
                match
        );
    }

    public Transaction fromDto(TransactionDto transactionDto) {
        return new Transaction(
                null,
                null,
                TransactionType.toEnum(transactionDto.getType()),
                transactionDto.getAmount(),
                transactionDto.getIsFree(),
                transactionDto.getDateTime(),
                null,
                null,
                null,
                null,
                null
        );
    }

    public void update(Transaction transaction) {
        Transaction transactionToUpdate = this.findById(transaction.getId());
        this.updateData(transactionToUpdate, transaction);
        transactionRepository.save(transactionToUpdate);
    }

    private void updateData(Transaction transactionToUpdate, Transaction transaction) {
        transactionToUpdate.setType(transaction.getType());
        transactionToUpdate.setAmount(transaction.getAmount());
        transactionToUpdate.setIsFree(transaction.getIsFree());
        transactionToUpdate.setDateTime(transaction.getDateTime());
    }
}
