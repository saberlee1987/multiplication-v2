package com.saber.gimificationv2.services;

import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.domains.GameResult;

public interface GameService {
    GameResult newAttemptForUser(ChallengeSolvedDto solvedDto);
}
