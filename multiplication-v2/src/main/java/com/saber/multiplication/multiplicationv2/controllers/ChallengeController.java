package com.saber.multiplication.multiplicationv2.controllers;

import com.saber.multiplication.multiplicationv2.dto.Challenge;
import com.saber.multiplication.multiplicationv2.services.ChallengeGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/challenges")
@Slf4j
public class ChallengeController {

    private ChallengeGeneratorService challengeGeneratorService;

    public ChallengeController(ChallengeGeneratorService challengeGeneratorService) {
        this.challengeGeneratorService = challengeGeneratorService;
    }

    @GetMapping(value = "/random",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Challenge> randomChallenge(){
        Challenge challenge =challengeGeneratorService.randomChallenge();
        log.info("challenge produce ===> {}",challenge);
        return ResponseEntity.ok(challenge);
    }
}
