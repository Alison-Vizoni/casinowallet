package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.CurrencyDto;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.models.entity.enums.CurrencyType;
import com.casinowallet.casinowallet.repository.CurrencyRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    public Currency findById(Long id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        return currency.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Currency not found! Id: ")
                .append(id).toString()));
    }

    public Currency findByCode(String code) {
        Optional<Currency> currency = currencyRepository.findByCode(code);
        return currency.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Currency not found! Code: ")
                .append(code).toString()));
    }

    public Currency insert(Currency currency) {
        currency.setId(null);
        try {
            return currencyRepository.save(currency);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving the currency in the database.", e);
        }
    }

    public Currency fromDto(CurrencyDto currencyDto) {
        if (null == currencyDto.getEnabled()) {
            currencyDto.setEnabled(true);
        }

        return new Currency(
                null,
                currencyDto.getCode(),
                currencyDto.getRate(),
                CurrencyType.toEnum(currencyDto.getType()),
                currencyDto.getEnabled()
        );
    }

    public void update(Currency newCurrency) {
        Currency currencyToUpdate = this.findById(newCurrency.getId());
        this.updateData(currencyToUpdate, newCurrency);
        currencyRepository.save(currencyToUpdate);
    }

    public void delete(Long id){
        this.findById(id);
        currencyRepository.deleteById(id);
    }

    private void updateData(Currency currencyToUpdate, Currency newCurrency) {
        currencyToUpdate.setCode(newCurrency.getCode());
        currencyToUpdate.setEnabled(newCurrency.getEnabled());
        currencyToUpdate.setType(newCurrency.getType());
        currencyToUpdate.setRate(newCurrency.getRate());
    }
}
