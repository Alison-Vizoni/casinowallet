package com.casinowallet.casinowallet.controller.core;

import com.casinowallet.casinowallet.models.dto.CurrencyDto;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.service.core.CurrencyService;
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
    public ResponseEntity<List<CurrencyDto>> findAll() {
        List<Currency> currencies = currencyService.findAll();
        List<CurrencyDto> currenciesDto = currencies.stream()
                .map(CurrencyDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(currenciesDto);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Currency> FindByCode(@PathVariable String code) {
        Currency currency = currencyService.findByCode(code);
        return ResponseEntity.ok().body(currency);
    }

    @PostMapping
    public ResponseEntity<Currency> insert(@Valid @RequestBody CurrencyDto currencyDto) {
        Currency currency = currencyService.fromDto(currencyDto);
        Currency newCurrency = currencyService.insert(currency);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCurrency);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CurrencyDto currencyDto, @PathVariable Long id) {
        Currency currency = currencyService.fromDto(currencyDto);
        currency.setId(id);
        currencyService.update(currency);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        currencyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
