package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.dto.core.PlayerDto;
import com.casinowallet.casinowallet.models.entity.Player;
import com.casinowallet.casinowallet.repository.PlayerRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player findById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Player not found! Id: ")
                .append(id).toString()));
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player insert(Player player) {
        player.setId(null);
        try {
            return playerRepository.save(player);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving the player in the database.", e);
        }
    }

    public void update(Player player) {
        Player playerToUpdate = this.findById(player.getId());
        this.updateData(playerToUpdate, player);
        playerRepository.save(playerToUpdate);
    }

    public void delete(Long id) {
        this.findById(id);
        playerRepository.deleteById(id);
    }

    public Player fromDto(PlayerDto playerDto) {
        return new Player(
                null,
                playerDto.getNickname(),
                null
        );
    }

    private void updateData(Player playerToUpdate, Player player) {
        playerToUpdate.setNickname(player.getNickname());
    }
}
