package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.dto.core.GameDto;
import com.casinowallet.casinowallet.models.dto.core.OpenGameDto;
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

    @Autowired
    private AccessService accessService;

    public Game findById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Game not found! Id ")
                .append(id).toString()));
    }

    public List<Game> findByProvider(Long id) {
        Optional<List<Game>> games = gameRepository.findByProviderId(id);
        return games.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Game not found! id: ")
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

    public String openGame(OpenGameDto openGameDto) {
        return accessService.createAccessToken(openGameDto);
    }

    public Game fromDto(GameDto gameDto) {
        Provider provider = providerService.findById(gameDto.getProviderId());
        return new Game(
                null,
                gameDto.getStrId(),
                GameType.toEnum(gameDto.getType()),
                provider
        );
    }

    public void update(Game game) {
        Game gameToUpdate = this.findById(game.getId());
        this.updateData(gameToUpdate, game);
        gameRepository.save(gameToUpdate);
    }

    public void delete(Long id) {
        this.findById(id);
        gameRepository.deleteById(id);
    }

    private void updateData(Game gameToUpdate, Game game) {
        gameToUpdate.setStrId(game.getStrId());
        gameToUpdate.setType(game.getType());
        gameToUpdate.setProvider(game.getProvider());
    }
}
