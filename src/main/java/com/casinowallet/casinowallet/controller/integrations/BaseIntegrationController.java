package com.casinowallet.casinowallet.controller.integrations;

import com.casinowallet.casinowallet.service.exceptions.MethodNotImplementedException;
import com.casinowallet.casinowallet.service.integrations.BaseIntegrationService;

public class BaseIntegrationController {
    public void validate(String signature, String playerToken) {
//        this.validateSignature(signature);
        this.validatePlayerToken(playerToken);
    }

    // TODO implement signature authentication
//    private void validateSignature(String signature) {
//        this.getIntegrationService().validateSignature(signature);
//    }

    private void validatePlayerToken(String token) {
        this.getIntegrationService().validatePlayerToken(token);
    }

    public BaseIntegrationService getIntegrationService() {
        throw new MethodNotImplementedException("Method getIntegrationService not implemented.");
    }
}
