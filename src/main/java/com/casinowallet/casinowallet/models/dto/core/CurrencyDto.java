package com.casinowallet.casinowallet.models.dto.core;

import com.casinowallet.casinowallet.models.entity.Currency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CurrencyDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Code is required.")
    @Length(min = 3, max = 5, message = "Code length must be between 3 and 5.")
    private String code;

    @Positive(message = "Rate must be positive.")
    @NotNull(message = "Rate is required.")
    private Double rate;

    @NotEmpty(message = "Type is required.")
    private String type;

    @NotNull(message = "Enabled can't be null.")
    private Boolean enabled = true;

    public CurrencyDto(Currency currency) {
        this.code = currency.getCode();
        this.rate = currency.getRate();
        this.type = currency.getType().getType();
        this.enabled = currency.getEnabled();
    }
}
