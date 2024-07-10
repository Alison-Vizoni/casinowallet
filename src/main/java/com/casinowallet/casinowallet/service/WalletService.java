package com.casinowallet.casinowallet.service;

import com.casinowallet.casinowallet.models.dto.WalletDto;
import com.casinowallet.casinowallet.models.entity.Player;
import com.casinowallet.casinowallet.models.entity.Wallet;
import com.casinowallet.casinowallet.repository.WalletRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private PlayerService playerService;

    public Wallet findById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        return wallet.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Object not found! Id: ")
                .append(id).toString()));
    }

    public List<Wallet> findAll() {
        return walletRepository.findAll();
    }

    public Wallet insert(Wallet wallet) {
        wallet.setId(null);
        try {
            return walletRepository.save(wallet);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving the wallet in the database.", e);
        }
    }

    public void update(Wallet wallet) {
        Wallet walletToUpdate = this.findById(wallet.getId());
        this.updateData(walletToUpdate, wallet);
        walletRepository.save(walletToUpdate);
    }

    public void delete(Long id) {
        this.findById(id);
        walletRepository.deleteById(id);
    }

    public Wallet fromDto(WalletDto walletDto) {
        currencyService.findByCode(walletDto.getBaseCurrency());
        Player player = playerService.findById(walletDto.getPlayerId());
        return new Wallet(
                null,
                walletDto.getBalance(),
                walletDto.getBaseCurrency(),
                player,
                null
        );
    }

    private void updateData(Wallet walletToUpdate, Wallet wallet) {
        walletToUpdate.setBaseCurrency(wallet.getBaseCurrency());
        walletToUpdate.setBalance(wallet.getBalance());
    }
}
