package com.casinowallet.casinowallet.controller.integrations.direct;

import com.casinowallet.casinowallet.controller.integrations.BaseIntegrationController;
import com.casinowallet.casinowallet.models.dto.integrations.direct.BalanceDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.BetDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.RollbackDto;
import com.casinowallet.casinowallet.models.dto.integrations.direct.WinDto;
import com.casinowallet.casinowallet.service.integrations.direct.DirectIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integration/direct/{providerStrId}")
public class DirectIntegrationController extends BaseIntegrationController {

    @Autowired
    protected DirectIntegrationService integrationService;

    @PostMapping("/balance")
    public ResponseEntity<BalanceDto> getBalance(
        @PathVariable String providerStrId,
// TODO implement signature authentication
//        @RequestHeader("X-Auth-Signature") String signature,
        @RequestHeader("X-Player-Token") String token
    ) {
        this.validate(null, token);
        integrationService.balance();
        BalanceDto balanceDto = new BalanceDto();
        return ResponseEntity.ok().body(balanceDto);
    }

    @PostMapping("/bet")
    public ResponseEntity<BetDto> bet(
        @PathVariable String providerStrId,
// TODO implement signature authentication
//        @RequestHeader("X-Auth-Signature") String signature,
        @RequestHeader("X-Player-Token") String token
    ) {
        this.validate(null, token);
        return null;
    }

    @PostMapping("/win")
    public ResponseEntity<WinDto> win(
        @PathVariable String providerStrId,
// TODO implement signature authentication
//        @RequestHeader("X-Auth-Signature") String signature,
        @RequestHeader("X-Player-Token") String token
    ) {
        this.validate(null, token);
        return null;
    }

    @PostMapping("/rollback")
    public ResponseEntity<RollbackDto> rollback(
        @PathVariable String providerStrId,
// TODO implement signature authentication
//        @RequestHeader("X-Auth-Signature") String signature,
        @RequestHeader("X-Player-Token") String token
    ) {
        this.validate(null, token);
        return null;
    }

    @Override
    public DirectIntegrationService getIntegrationService() {
        return integrationService;
    }
}
