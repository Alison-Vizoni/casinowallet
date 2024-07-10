package com.casinowallet.casinowallet.controller;

import com.casinowallet.casinowallet.models.dto.PlayerDto;
import com.casinowallet.casinowallet.models.entity.Player;
import com.casinowallet.casinowallet.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id) {
        Player player = playerService.findById(id);
        return ResponseEntity.ok().body(player);
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> findAll() {
        List<Player> players = playerService.findAll();
        List<PlayerDto> playersDto = players.stream()
                .map(PlayerDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(playersDto);
    }

    @PostMapping
    public ResponseEntity<Player> insert(@Valid @RequestBody PlayerDto playerDto) {
        Player player = playerService.fromDto(playerDto);
        Player newPlayer = playerService.insert(player);
        return ResponseEntity.ok().body(newPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody PlayerDto playerDto, @PathVariable Long id) {
        Player player = playerService.fromDto(playerDto);
        player.setId(id);;
        playerService.update(player);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
