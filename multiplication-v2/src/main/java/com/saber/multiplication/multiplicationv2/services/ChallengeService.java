package com.saber.multiplication.multiplicationv2.services;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;

public interface ChallengeService {
    ChallengeAttempt verifying(ChallengeAttemptDto challengeAttemptDto);
}
