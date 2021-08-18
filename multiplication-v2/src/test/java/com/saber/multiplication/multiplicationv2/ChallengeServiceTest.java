package com.saber.multiplication.multiplicationv2;

import com.saber.multiplication.multiplicationv2.client.GamificationServiceClient;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;
import com.saber.multiplication.multiplicationv2.dto.StatsUserAttemptDto;
import com.saber.multiplication.multiplicationv2.dto.User;
import com.saber.multiplication.multiplicationv2.repositories.ChallengeAttemptRepository;
import com.saber.multiplication.multiplicationv2.repositories.UserRepository;
import com.saber.multiplication.multiplicationv2.services.ChallengeService;
import com.saber.multiplication.multiplicationv2.services.impl.ChallengeServiceImpl;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

    private ChallengeService challengeService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChallengeAttemptRepository challengeAttemptRepository;
    @Mock
    private GamificationServiceClient serviceClient;

    private static final Logger log = LoggerFactory.getLogger(ChallengeServiceTest.class);

    @BeforeEach
    public void before(){
        this.challengeService = new ChallengeServiceImpl(userRepository,challengeAttemptRepository,serviceClient);


    }

    @Test
    public void checkCorrectAttemptTest(){
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50,60,"saber",3000);

        BDDMockito.given(challengeAttemptRepository.save(ArgumentMatchers.any()))
        .will(AdditionalAnswers.returnsFirstArg());

        ChallengeAttempt challengeAttempt = challengeService.verifying(attemptDto);

        log.info("challenge Attempt Response ===> {}",challengeAttempt);
        BDDAssertions.then(challengeAttempt.getCorrect()).isEqualTo(true);
        BDDMockito.verify(userRepository).save(new User(null,"saber"));
        BDDMockito.verify(challengeAttemptRepository).save(challengeAttempt);
    }

    @Test
    public void checkWrongAttemptTest(){
        ChallengeAttemptDto attemptDto = new ChallengeAttemptDto(50,60,"saber",5000);

        BDDMockito.given(challengeAttemptRepository.save(ArgumentMatchers.any()))
                .will(AdditionalAnswers.returnsFirstArg());

        ChallengeAttempt challengeAttempt = challengeService.verifying(attemptDto);

        log.info("challenge Attempt Response ===> {}",challengeAttempt);
        BDDAssertions.then(challengeAttempt.getCorrect()).isFalse();
        BDDMockito.verify(userRepository).save(new User(1L,"saber"));
        BDDMockito.verify(challengeAttemptRepository).save(challengeAttempt);
    }

    @Test
    public void checkExistingUserTest(){

        BDDMockito.given(challengeAttemptRepository.save(ArgumentMatchers.any()))
                .will(AdditionalAnswers.returnsFirstArg());

        User existingUser = new User(1L,"saber");

        BDDMockito.given(userRepository.findByAlias("saber"))
                .willReturn(Optional.of(existingUser));

        ChallengeAttemptDto attemptDto =new ChallengeAttemptDto(50,60,"saber",5000);

        ChallengeAttempt resultAttempt = challengeService.verifying(attemptDto);

        BDDAssertions.then(resultAttempt.getCorrect()).isFalse();
        BDDAssertions.then(resultAttempt.getUser()).isEqualTo(existingUser);
        BDDMockito.verify(userRepository, Mockito.never()).save(ArgumentMatchers.any());
        BDDMockito.verify(challengeAttemptRepository).save(resultAttempt);

    }

    @Test
    public void retrieveStatsTest(){
        // given
        User user = new User("saber");
        ChallengeAttempt attempt1 = new ChallengeAttempt(1L, user, 50, 60, 3010, false);
        ChallengeAttempt attempt2 = new ChallengeAttempt(2L, user, 50, 60, 3051, false);
        List<ChallengeAttempt> lastAttempts = List.of(attempt1, attempt2);
        BDDMockito.given(challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc("saber"))
                .willReturn(lastAttempts);

        // when
       StatsUserAttemptDto latestAttemptsResult =
                challengeService.getStatsForUser("saber");

        BDDAssertions.then(latestAttemptsResult.getAttempts()).isEqualTo(lastAttempts);
    }
}
