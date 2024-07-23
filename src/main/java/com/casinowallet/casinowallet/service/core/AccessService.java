package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.dto.OpenGameDto;
import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.models.entity.Game;
import com.casinowallet.casinowallet.models.entity.Player;
import com.casinowallet.casinowallet.repository.AccessRepository;
import com.casinowallet.casinowallet.security.PlayerJwtUtil;
import com.casinowallet.casinowallet.service.exceptions.AccessExpiredException;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.InvalidAccessException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public Access findById(Long id) {
        Optional<Access> access = accessRepository.findById(id);
        return access.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Access not found! Id: ")
                .append(id).toString()));
    }

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

        return PlayerJwtUtil.generateToken(claims);
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

    public void validateAccess(Access access) {
        if (access == null) {
            throw new InvalidAccessException("Access is null.");
        }

        if (access.getExpired()) {
            throw new AccessExpiredException("Access expired");
        }
    }

    public void expireAccess(Access access) {
        if (access.getExpiresAt().isBefore(Instant.now())){
            access.setExpired(true);
            accessRepository.save(access);
        }
    }
}
