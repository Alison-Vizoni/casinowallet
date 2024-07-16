package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.OpenGameDto;
import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.models.entity.Game;
import com.casinowallet.casinowallet.models.entity.Player;
import com.casinowallet.casinowallet.repository.AccessRepository;
import com.casinowallet.casinowallet.security.PlayerJwtUtil;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccessService {

    @Autowired
    private AccessRepository accessRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CurrencyService currencyService;

    @Value("${access.expiration}")
    private Long expiration;

    @Autowired
    private PlayerJwtUtil playerJwtUtil;

    public String createAccessToken(OpenGameDto openGameDto) {
        Game game = gameService.findById(openGameDto.getGameId());
        Player player = playerService.findById(openGameDto.getPlayerId());
        Currency currency = currencyService.findByCode(openGameDto.getCurrencyCode());

        this.expirePlayerOlderAccess(player.getId());

        Access access = this.insert(new Access(
                null,
                Instant.now(),
                Instant.now().plusSeconds(this.expiration),
                currency.getRate(),
                currency.getCode(),
                false,
                player,
                game,
                null
        ));

        Map<String, String> claims = new HashMap<>();
        claims.put("access", access.getId().toString());
        claims.put("game", game.getStrId());
        claims.put("currency", currency.getCode());

        return playerJwtUtil.generateToken(claims);
    }

    private Access insert(Access access) {
        access.setId(null);
        try {
            return accessRepository.save(access);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving access in the database.", e);
        }
    }

    private void expirePlayerOlderAccess(Long playerId) {
        accessRepository.expirePlayerAccess(playerId, Instant.now().minusSeconds(this.expiration));
    }
}
