package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.models.entity.enums.CurrencyType;
import com.casinowallet.casinowallet.repository.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.logging.Logger;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CurrencyServiceTest {

    private final Logger LOG = Logger.getLogger(CurrencyServiceTest.class.getName());

    @MockBean
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void whenSaveCurrency_theReturnCurrencyWithId() {
        LOG.info("TEST SAVING SUCCESSFUL");

        String code = "USD";
        Double rate = 1.0;
        CurrencyType type = CurrencyType.FIAT;

        Currency currency = new Currency(null, code, rate, type, true);
        Currency currencyExpected = new Currency(1L, code, rate, type, true);

        when(currencyRepository.save(any(Currency.class))).thenReturn(currencyExpected);
        Currency currencySaved = currencyService.insert(currency);

        Assertions.assertNotNull(currencySaved);
        Assertions.assertEquals(currencyExpected.getId(), currencySaved.getId());
        Assertions.assertEquals(currencyExpected.getCode(), currencySaved.getCode());
        Assertions.assertEquals(currencyExpected.getRate(), currencySaved.getRate());
        Assertions.assertEquals(currencyExpected.getType(), currencySaved.getType());
        Assertions.assertEquals(currencyExpected.getEnabled(), currencySaved.getEnabled());
    }
}
