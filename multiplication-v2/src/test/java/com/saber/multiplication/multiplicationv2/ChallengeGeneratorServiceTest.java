package com.saber.multiplication.multiplicationv2;

import com.saber.multiplication.multiplicationv2.dto.Challenge;
import com.saber.multiplication.multiplicationv2.services.ChallengeGeneratorService;
import com.saber.multiplication.multiplicationv2.services.impl.ChallengeGeneratorServiceImpl;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
public class ChallengeGeneratorServiceTest {

    private ChallengeGeneratorService challengeGeneratorService;

    private static final Logger log = LoggerFactory.getLogger(ChallengeGeneratorServiceTest.class);

    @Spy
//    @Mock
    private Random random;

    @BeforeEach
    public void  before(){
        challengeGeneratorService= new ChallengeGeneratorServiceImpl(random);
    }

    @Test
    public void generatorRandomFactorBetweenExpectedLimits(){
        BDDMockito.given(random.nextInt(89)).willReturn(20,30);

        Challenge challenge = challengeGeneratorService.randomChallenge();

        log.info("challenge is ===> {}",challenge);
        BDDAssertions.then(challenge).isEqualTo(new Challenge(31,41));

    }
}
