package com.casinowallet.casinowallet.models.dto.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.BaseWinDto;
import com.casinowallet.casinowallet.models.entity.Transaction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class WinDto extends BaseWinDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public WinDto(Transaction transaction){

    }
}
