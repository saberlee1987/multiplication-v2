package com.saber.multiplication.multiplicationv2;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;
import com.saber.multiplication.multiplicationv2.services.ChallengeService;
import com.saber.multiplication.multiplicationv2.services.impl.ChallengeServiceImpl;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

    private ChallengeService challengeService;

    private static final Logger log = LoggerFactory.getLogger(ChallengeServiceTest.class);

    @BeforeEach
    public void before(){
        this.challengeService = new ChallengeServiceImpl();
    }

    @Test
    public void checkCorrectAttemptTest(){
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50,60,"saber",3000);

        ChallengeAttempt challengeAttempt = challengeService.verifying(attemptDto);

        log.info("challenge Attempt Response ===> {}",challengeAttempt);
        BDDAssertions.then(challengeAttempt.getCorrect()).isEqualTo(true);

    }

    @Test
    public void checkWrongAttemptTest(){
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50,60,"saber",5000);

        ChallengeAttempt challengeAttempt = challengeService.verifying(attemptDto);

        log.info("challenge Attempt Response ===> {}",challengeAttempt);
        BDDAssertions.then(challengeAttempt.getCorrect()).isFalse();

    }
}
