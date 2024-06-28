package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.CurrencyDto;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.models.entity.enums.CurrencyType;
import com.casinowallet.casinowallet.repository.CurrencyRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> findAll() {
        return currencyRepository.findAll();
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
}
