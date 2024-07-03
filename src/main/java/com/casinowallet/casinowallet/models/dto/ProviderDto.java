package com.casinowallet.casinowallet.models.dto;

import com.casinowallet.casinowallet.models.entity.Provider;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProviderDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "StrId is required.")
    @Length(min = 2, max = 50, message = "StrId length must be between 2 and 50.")
    private String strId;

    @NotEmpty(message = "Name is required")
    @Length(min = 2, max = 70, message = "Name length must be between 2 and 70.")
    private String name;

    public ProviderDto(Provider provider) {
        this.strId = provider.getStrId();
        this.name = provider.getName();
    }
}
