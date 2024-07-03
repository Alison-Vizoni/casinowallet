package com.casinowallet.casinowallet.controller;

import com.casinowallet.casinowallet.models.dto.ProviderDto;
import com.casinowallet.casinowallet.models.entity.Provider;
import com.casinowallet.casinowallet.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/provider")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping
    public ResponseEntity<List<ProviderDto>> findAll() {
        List<Provider> providers = providerService.findAll();
        List<ProviderDto> providersDto = providers.stream()
                .map(ProviderDto::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(providersDto);
    }

    @GetMapping("/{strId}")
    public ResponseEntity<Provider> findByStrId(@PathVariable String strId){
        Provider provider = providerService.findByStrId(strId);
        return ResponseEntity.ok().body(provider);
    }

    @PostMapping
    public ResponseEntity<Provider> insert(@Valid @RequestBody ProviderDto providerDto) {
        Provider provider = providerService.fromDto(providerDto);
        Provider newProvider = providerService.insert(provider);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProvider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ProviderDto providerDto, @PathVariable Long id) {
        Provider provider = providerService.fromDto(providerDto);
        provider.setId(id);
        providerService.update(provider);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        providerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
