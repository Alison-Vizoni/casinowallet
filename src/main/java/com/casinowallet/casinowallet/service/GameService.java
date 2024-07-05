package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.GameNewDto;
import com.casinowallet.casinowallet.models.entity.Game;
import com.casinowallet.casinowallet.models.entity.Provider;
import com.casinowallet.casinowallet.models.entity.enums.GameType;
import com.casinowallet.casinowallet.repository.GameRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ProviderService providerService;

    public List<Game> findByProvider(Long id) {
        Optional<List<Game>> games = gameRepository.findByProviderId(id);
        return games.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Object not found! id: ")
                .append(id).toString()));
    }

    public Game insert(Game game) {
        game.setId(null);
        try {
            return gameRepository.save(game);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving the game in the database.", e);
        }
    }

    public Game fromDto(GameNewDto gameNewDto) {
        Provider provider = providerService.findById(gameNewDto.getProviderId());
        return new Game(
                null,
                gameNewDto.getStrId(),
                GameType.toEnum(gameNewDto.getType()),
                provider
        );
    }
}
