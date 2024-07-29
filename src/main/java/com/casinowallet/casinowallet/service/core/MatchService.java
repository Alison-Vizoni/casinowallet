package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.Match;
import com.casinowallet.casinowallet.repository.MatchRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Match findByExternalId(Long gameId, String externalId) {
        Optional<Match> match = matchRepository.findByGameIdAndExternalId(gameId, externalId);
        return match.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Match not found! externalId: ")
                .append(externalId).toString()));
    }

    public void finishMatch(Long id) {
        matchRepository.finishMatch(id);
    }

    public Match findOrCreateMatch(String externalId, Access access) {
        Optional<Match> match = matchRepository.findByGameIdAndExternalId(access.getGame().getId(), externalId);

        if (match.isEmpty()) {
            Match newMatch = new Match(
                    null,
                    externalId,
                    false,
                    null,
                    access.getGame(),
                    access
            );
            return this.insert(newMatch);
        }

        return match.get();
    }

    public Match insert(Match match){
        match.setId(null);
        try {
            return this.matchRepository.save(match);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving the match in the database.", e);
        }
    }
}
