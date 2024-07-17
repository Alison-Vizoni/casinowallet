package com.casinowallet.casinowallet.controller;

import com.casinowallet.casinowallet.models.dto.TransactionDto;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = transactionService.findById(id);
        return ResponseEntity.ok().body(transaction);
    }

    @GetMapping("/external/{externalId}")
    public ResponseEntity<Transaction> findByExternalId(@PathVariable String externalId) {
        Transaction transaction = transactionService.findByExternalId(externalId);
        return ResponseEntity.ok().body(transaction);
    }

    @PostMapping
    public ResponseEntity<Transaction> insert(@Valid @RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionService.fromDto(transactionDto);
        Transaction newTransaction = transactionService.insert(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }
}
