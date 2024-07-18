package com.casinowallet.casinowallet.models.dto.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.BaseRollbackDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RollbackDto extends BaseRollbackDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
