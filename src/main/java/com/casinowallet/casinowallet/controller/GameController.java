package com.casinowallet.casinowallet.controller;

import com.casinowallet.casinowallet.models.dto.GameDto;
import com.casinowallet.casinowallet.models.dto.GameNewDto;
import com.casinowallet.casinowallet.models.entity.Game;
import com.casinowallet.casinowallet.service.GameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/provider/{id}")
    public ResponseEntity<List<GameDto>> findByProvider(@PathVariable Long id) {
        List<Game> games = gameService.findByProvider(id);
        List<GameDto> gamesDto = games.stream()
                .map(GameDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(gamesDto);
    }

    @PostMapping
    public ResponseEntity<Game> insert(@Valid @RequestBody GameNewDto gameNewDto) {
        Game game = gameService.fromDto(gameNewDto);
        Game newGame = gameService.insert(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);
    }
}
