package com.casinowallet.casinowallet.controller.integrations.direct;

import com.casinowallet.casinowallet.models.dto.integrations.direct.BalanceDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.BetDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.RollbackDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.WinDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration/direct")
public class DirectIntegrationController {

    @PostMapping("/{providerStrId}/balance")
    public ResponseEntity<BalanceDto> getBalance() {
        BalanceDto balanceDto = new BalanceDto();
        return ResponseEntity.ok().body(balanceDto);
    }

    @PostMapping("/")
    public ResponseEntity<BetDto> bet() {
        return null;
    }

    public ResponseEntity<WinDto> win() {
        return null;
    }

    public ResponseEntity<RollbackDto> rollback() {
        return null;
    }
}
