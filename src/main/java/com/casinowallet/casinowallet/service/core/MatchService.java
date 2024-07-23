package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.entity.Match;
import com.casinowallet.casinowallet.repository.MatchRepository;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;

    public Match findById(Long id) {
        Optional<Match> match = matchRepository.findById(id);
        return match.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Match not found! id: ")
                .append(id).toString()));
    }

    public Match findByExternalId(String externalId) {
        Optional<Match> match = matchRepository.findByExternalId(externalId);
        return match.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Match not found! externalId: ")
                .append(externalId).toString()));
    }

    public void finishMatch(Long id) {
        matchRepository.finishMatch(id);
    }
}
