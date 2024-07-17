package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.TransactionDto;
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

    public Transaction findByExternalId(String externalId) {
        Optional<Transaction> transaction = transactionRepository.findByExternalId(externalId);
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

    public Transaction fromDto(TransactionDto transactionDto) {
        Match match = matchService.findByExternalId(transactionDto.getExternalId());
        Access access = match.getAccess();
        Game game = access.getGame();

        if (null == transactionDto.getIsFree()) {
            transactionDto.setIsFree(false);
        }

        return new Transaction(
                null,
                transactionDto.getExternalId(),
                TransactionType.toEnum(transactionDto.getType()),
                transactionDto.getAmount(),
                transactionDto.getIsFree(),
                transactionDto.getDateTime(),
                access.getCurrencyCode(),
                game.getId(),
                game.getProvider(),
                access.getPlayer().getWallet(),
                match
        );
    }
}
