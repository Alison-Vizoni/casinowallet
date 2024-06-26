package com.casinowallet.casinowallet.controller;

import com.casinowallet.casinowallet.models.dto.CurrencyDto;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.service.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getAll() {
        List<Currency> currencies = currencyService.findAll();
        List<CurrencyDto> currenciesDto = currencies.stream()
                .map(CurrencyDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(currenciesDto);
    }

    @PostMapping
    public ResponseEntity<Currency> insert(@Valid @RequestBody CurrencyDto currencyDto) {
        Currency currency = currencyService.fromDto(currencyDto);
        currency = currencyService.insert(currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(currency);
    }
}
