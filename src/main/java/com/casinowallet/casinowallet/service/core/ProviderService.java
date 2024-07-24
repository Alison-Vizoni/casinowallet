package com.casinowallet.casinowallet.service.core;

import com.casinowallet.casinowallet.models.dto.core.ProviderDto;
import com.casinowallet.casinowallet.models.entity.Provider;
import com.casinowallet.casinowallet.repository.ProviderRepository;
import com.casinowallet.casinowallet.service.exceptions.DataIntegrityException;
import com.casinowallet.casinowallet.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public List<Provider> findAll() {
        return providerRepository.findAll();
    }

    public Provider findById(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        return provider.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Provider not found! Id: ")
                .append(id).toString()));
    }

    public Provider findByStrId(String strId) {
        Optional<Provider> provider = providerRepository.findByStrId(strId);
        return provider.orElseThrow(() -> new ObjectNotFoundException(new StringBuilder()
                .append("Provider not found! strId: ")
                .append(strId).toString()));
    }

    public Provider insert(Provider provider) {
        provider.setId(null);
        try {
            return providerRepository.save(provider);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Error saving the provider in the database.", e);
        }
    }

    public void update(Provider newProvider) {
        Provider providerToUpdate = this.findById(newProvider.getId());
        this.updateData(providerToUpdate, newProvider);
        providerRepository.save(providerToUpdate);
    }

    public Provider fromDto(ProviderDto providerDto) {
        return new Provider(
                null,
                providerDto.getStrId(),
                providerDto.getName(),
                null
        );
    }

    public void delete(Long id) {
        this.findById(id);
        providerRepository.deleteById(id);
    }

    private void updateData(Provider providerToUpdate, Provider newProvider) {
        providerToUpdate.setName(newProvider.getName());
        providerToUpdate.setStrId(newProvider.getStrId());
    }
}
