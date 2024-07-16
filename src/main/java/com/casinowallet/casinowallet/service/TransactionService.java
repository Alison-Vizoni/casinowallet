package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.TransactionDto;
import com.casinowallet.casinowallet.models.entity.Transaction;
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

    public Transaction findById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        return transaction.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Transaction not found! Id: ")
                .append(id).toString()));
    }

    public Transaction findByExternalId(Long id) {
        Optional<Transaction> transaction = transactionRepository.findByExternalId(id);
        return transaction.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Transaction not found! Id: ")
                .append(id).toString()));
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

        return new Transaction();
    }
}
