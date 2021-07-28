package com.saber.multiplication.multiplicationv2.services.impl;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;
import com.saber.multiplication.multiplicationv2.dto.User;
import com.saber.multiplication.multiplicationv2.services.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private static final Logger log = LoggerFactory.getLogger(ChallengeServiceImpl.class);

    @Override
    public ChallengeAttempt verifying(ChallengeAttemptDto challengeAttemptDto) {

        log.info("verifying challengeAttempt ====> {}",challengeAttemptDto);
        Integer correctResult = challengeAttemptDto.getFactorA() * challengeAttemptDto.getFactorB();
        Boolean isCorrect = challengeAttemptDto.getGuess().equals(correctResult);
        User user = new User(null, challengeAttemptDto.getUserAlias());
        ChallengeAttempt challengeAttempt = new ChallengeAttempt();
        challengeAttempt.setCorrect(isCorrect);
        challengeAttempt.setFactorA(challengeAttemptDto.getFactorA());
        challengeAttempt.setFactorB(challengeAttemptDto.getFactorB());
        challengeAttempt.setResultAttempt(challengeAttemptDto.getGuess());
        challengeAttempt.setUser(user);
        return challengeAttempt;
    }
}