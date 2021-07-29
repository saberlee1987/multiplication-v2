package com.saber.multiplication.multiplicationv2.controllers;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;
import com.saber.multiplication.multiplicationv2.services.ChallengeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/challenges")
@RequiredArgsConstructor
@Slf4j
public class ChallengeAttemptController {

    private final ChallengeService challengeService;

    @PostMapping(value = "/attempt",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChallengeAttempt> attempt(@RequestBody @Valid @NotNull(message = "challengeAttemptDto is Required") ChallengeAttemptDto attemptDto){
        log.info("Request for attempt with body ===> {}",attemptDto);
        ChallengeAttempt challengeAttempt = challengeService.verifying(attemptDto);
        log.info("Response for attempt ===> {}",challengeAttempt);
        return ResponseEntity.ok(challengeAttempt);
    }
}
