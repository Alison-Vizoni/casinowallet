package com.casinowallet.casinowallet.controller.exception.integrations;

public enum DirectIntegrationStatus {
    OBJECT_NOT_EXISTS(1000);

    private final Integer value;

    DirectIntegrationStatus(Integer value) {
        this.value = value;
    }

    public Integer value() {
        return this.value;
    }
}
