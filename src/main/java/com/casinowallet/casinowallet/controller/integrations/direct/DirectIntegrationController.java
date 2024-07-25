package com.casinowallet.casinowallet.controller.integrations.direct;

import com.casinowallet.casinowallet.controller.integrations.BaseIntegrationController;
import com.casinowallet.casinowallet.models.dto.integrations.direct.*;
import com.casinowallet.casinowallet.models.entity.Transaction;
import com.casinowallet.casinowallet.service.integrations.direct.DirectIntegrationService;
import jakarta.validation.Valid;
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
        BalanceDto balanceDto = this.lockContext(integrationService::balance);
        return ResponseEntity.ok().body(balanceDto);
    }

    @PostMapping("/bet")
    public ResponseEntity<BetDto> bet(
        @PathVariable String providerStrId,
// TODO implement signature authentication
//        @RequestHeader("X-Auth-Signature") String signature,
        @RequestHeader("X-Player-Token") String token,
        @Valid @RequestBody BetNewDto betNewDto
    ) {
        this.validate(null, token);
        Transaction transaction = integrationService.fromDto(betNewDto);
        BetDto betDto = this.lockContext(integrationService::bet, transaction);
        return ResponseEntity.ok().body(betDto);
    }

    @PostMapping("/win")
    public ResponseEntity<WinDto> win(
        @PathVariable String providerStrId,
// TODO implement signature authentication
//        @RequestHeader("X-Auth-Signature") String signature,
        @RequestHeader("X-Player-Token") String token,
        @Valid @RequestBody WinNewDto winNewDto
    ) {
        this.validate(null, token);
        Transaction transaction = integrationService.fromDto(winNewDto);
        WinDto winDto = this.lockContext(integrationService::win, transaction);
        return ResponseEntity.ok().body(winDto);
    }

    @PostMapping("/rollback")
    public ResponseEntity<RollbackDto> rollback(
        @PathVariable String providerStrId,
// TODO implement signature authentication
//        @RequestHeader("X-Auth-Signature") String signature,
        @RequestHeader("X-Player-Token") String token,
        @Valid @RequestBody BetNewDto betNewDto
    ) {
        this.validate(null, token);
        Transaction transaction = integrationService.fromDto(betNewDto);
        RollbackDto rollbackDto = this.lockContext(integrationService::rollback, transaction);
        return ResponseEntity.ok().body(rollbackDto);
    }

    @Override
    public DirectIntegrationService getIntegrationService() {
        return integrationService;
    }
}
