package com.casinowallet.casinowallet.models.dto;

import com.casinowallet.casinowallet.models.entity.Currency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Code is required")
    private String code;

    @Positive
    private Double rate;

    @NotEmpty(message = "Type is required")
    private String type;

    private Boolean enabled;

    public CurrencyDto(Currency currency) {
        this.code = currency.getCode();
        this.rate = currency.getRate();
        this.type = currency.getType().getName();
        this.enabled = currency.getEnabled();
    }
}
