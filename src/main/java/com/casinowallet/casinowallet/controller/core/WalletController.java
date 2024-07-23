package com.casinowallet.casinowallet.controller.core;

import com.casinowallet.casinowallet.models.dto.WalletDto;
import com.casinowallet.casinowallet.models.entity.Wallet;
import com.casinowallet.casinowallet.service.core.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findById(@PathVariable Long id) {
        Wallet wallet = walletService.findById(id);
        return ResponseEntity.ok().body(wallet);
    }

    @GetMapping
    public ResponseEntity<List<WalletDto>> findAll() {
        List<Wallet> wallets = walletService.findAll();
        List<WalletDto> walletsDto = wallets.stream()
                .map(WalletDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(walletsDto);
    }

    @PostMapping
    public ResponseEntity<Wallet> insert(@Valid @RequestBody WalletDto walletDto) {
        Wallet wallet = walletService.fromDto(walletDto);
        Wallet newWallet = walletService.insert(wallet);
        return ResponseEntity.ok().body(newWallet);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody WalletDto walletDto, @PathVariable Long id) {
        Wallet wallet = walletService.fromDto(walletDto);
        wallet.setId(id);
        walletService.update(wallet);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
