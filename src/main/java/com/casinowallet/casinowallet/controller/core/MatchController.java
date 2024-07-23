package com.casinowallet.casinowallet.controller.core;

import com.casinowallet.casinowallet.models.entity.Match;
import com.casinowallet.casinowallet.service.core.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/match")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping("/{id}")
    public ResponseEntity<Match> findById(@PathVariable Long id) {
        Match match = matchService.findById(id);
        return ResponseEntity.ok().body(match);
    }

    @GetMapping("/external/{externalId}")
    public ResponseEntity<Match> findById(@PathVariable String externalId) {
        Match match = matchService.findByExternalId(externalId);
        return ResponseEntity.ok().body(match);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> finishMatch(@PathVariable Long id){
        matchService.finishMatch(id);
        return ResponseEntity.noContent().build();
    }
}
