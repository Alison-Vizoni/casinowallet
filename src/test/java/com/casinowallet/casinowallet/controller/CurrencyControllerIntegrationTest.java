package com.casinowallet.casinowallet.controller;

import com.casinowallet.casinowallet.controller.core.CurrencyController;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.service.core.CurrencyService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.casinowallet.casinowallet.utils.GeneratorUtil.generateCurrency;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(value = CurrencyController.class)
public class CurrencyControllerIntegrationTest {

    private final Logger LOG = Logger.getLogger(CurrencyControllerIntegrationTest.class.getName());

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mvc);
    }

    @Test
    void givenCurrencies_whenGetAllCurrencies_thenReturnJsonArray() throws Exception {
        LOG.info("TEST GET ALL CURRENCIES.");

        Currency currency = generateCurrency();
        Currency currency1 = generateCurrency();
        List<Currency> allCurrencies = Arrays.asList(currency, currency1);

        given(currencyService.findAll()).willReturn(allCurrencies);

        mvc.perform(get("/currency").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
