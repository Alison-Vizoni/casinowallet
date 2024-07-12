package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.OpenGameDto;
import com.casinowallet.casinowallet.models.entity.Access;
import com.casinowallet.casinowallet.models.entity.Currency;
import com.casinowallet.casinowallet.models.entity.Game;
import com.casinowallet.casinowallet.models.entity.Player;
import com.casinowallet.casinowallet.repository.AccessRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
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

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private final SecretKey key;

    AccessService() {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(this.secret));
    }

    public String createAccessToken(OpenGameDto openGameDto) {
        Game game = gameService.findById(openGameDto.getGameId());
        Player player = playerService.findById(openGameDto.getPlayerId());
        Currency currency = currencyService.findByCode(openGameDto.getCurrencyCode());

        Access access = this.insert(new Access(
                null,
                Instant.now(),
                Instant.now().plusSeconds(this.expiration),
                currency.getRate(),
                currency.getCode(),
                player,
                game,
                null
        ));

        Map<String, String> claims = new HashMap<>();
        claims.put("access", access.getId().toString());
        claims.put("game", game.getStrId());
        claims.put("currency", currency.getCode());

        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + this.expiration))
                .signWith(key)
                .compact();
    }

    private Access insert(Access access) {
        access.setId(null);
        try {
            return accessRepository.save(access);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving access in the database.", e);
        }
    }

}
