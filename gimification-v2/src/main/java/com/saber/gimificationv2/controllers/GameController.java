package com.saber.gimificationv2.controllers;

import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.services.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/gamification")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @PostMapping("/attempts")
    @ResponseStatus(HttpStatus.OK)
    public void postResult(@RequestBody @Valid ChallengeSolvedDto solvedDto){
        log.info("Request for postResult solveDto ===> {} ",solvedDto);
        gameService.newAttemptForUser(solvedDto);
    }
}
