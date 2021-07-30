package com.saber.multiplication.multiplicationv2.services;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;
import com.saber.multiplication.multiplicationv2.dto.StatsUserAttemptDto;

public interface ChallengeService {
    ChallengeAttempt verifying(ChallengeAttemptDto challengeAttemptDto);

    StatsUserAttemptDto getStatsForUser(String userAlias);
}
