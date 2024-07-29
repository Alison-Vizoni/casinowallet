package com.casinowallet.casinowallet.controller.integrations;

import com.casinowallet.casinowallet.controller.utils.RedisConnection;
import com.casinowallet.casinowallet.service.integrations.BaseIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class BaseIntegrationController {

    @Autowired
    private RedisConnection redisConnection;

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

    public abstract BaseIntegrationService getIntegrationService();

    public <T, R> R lockContext(Function<T, R> method, T param) {
        R result = null;
        String lockKey = this.getIntegrationService().getLockKey();
        try {
            redisConnection.acquireLock(lockKey);
            result = method.apply(param);
        } catch (Exception e) {
            System.out.println("Something wrong. " + e.getMessage());
        } finally {
            redisConnection.close();
        }

        return result;
    }

    public <R> R lockContext(Supplier<R> method) {
        R result = null;
        String lockKey = this.getIntegrationService().getLockKey();
        try{
            redisConnection.acquireLock(lockKey);
            result = method.get();
        } catch (Exception e) {
            System.out.println("Something wrong. " + e.getMessage());
        } finally {
            redisConnection.close();
        }

        return result;
    }
}
