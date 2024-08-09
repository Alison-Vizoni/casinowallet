package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.LastMatch;
import com.casinowallet.casinowallet.repository.LastMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LastMatchService {

    @Autowired
    private LastMatchRepository lastMatchRepository;

    public LastMatch getLastMatch(Access access) {
        Optional<LastMatch> lastMatch = lastMatchRepository.findByGameIdCurrencyCodePlayerId(
                access.getGame().getId(),
                access.getCurrencyCode(),
                access.getPlayer().getId()
        );
        return lastMatch.orElse(null);
    }

    public void updateLastMatch(LastMatch lastMatch) {
        lastMatchRepository.save(lastMatch);
    }
}
