package com.saber.multiplication.multiplicationv2.services.impl;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeAttemptDto;
import com.saber.multiplication.multiplicationv2.dto.StatsUserAttemptDto;
import com.saber.multiplication.multiplicationv2.dto.User;
import com.saber.multiplication.multiplicationv2.repositories.ChallengeAttemptRepository;
import com.saber.multiplication.multiplicationv2.repositories.UserRepository;
import com.saber.multiplication.multiplicationv2.services.ChallengeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private static final Logger log = LoggerFactory.getLogger(ChallengeServiceImpl.class);

    private final UserRepository userRepository;
    private final ChallengeAttemptRepository challengeAttemptRepository;

    public ChallengeServiceImpl(UserRepository userRepository, ChallengeAttemptRepository challengeAttemptRepository) {
        this.challengeAttemptRepository = challengeAttemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ChallengeAttempt verifying(ChallengeAttemptDto challengeAttemptDto) {

        log.info("verifying challengeAttempt ====> {}", challengeAttemptDto);
        Integer correctResult = challengeAttemptDto.getFactorA() * challengeAttemptDto.getFactorB();
        Boolean isCorrect = challengeAttemptDto.getGuess().equals(correctResult);
        User user = userRepository.findByAlias(challengeAttemptDto.getUserAlias())
                .orElseGet(() -> {
                    log.info("Created new User with alias {}", challengeAttemptDto.getUserAlias());
                    return userRepository.save(new User(challengeAttemptDto.getUserAlias()));
                });
        ChallengeAttempt challengeAttempt = new ChallengeAttempt();
        challengeAttempt.setCorrect(isCorrect);
        challengeAttempt.setFactorA(challengeAttemptDto.getFactorA());
        challengeAttempt.setFactorB(challengeAttemptDto.getFactorB());
        challengeAttempt.setResultAttempt(challengeAttemptDto.getGuess());
        challengeAttempt.setUser(user);
        return this.challengeAttemptRepository.save(challengeAttempt);
    }

    @Override
    @Transactional(readOnly = true)
    public StatsUserAttemptDto getStatsForUser(String userAlias) {
        List<ChallengeAttempt> attempts = this.challengeAttemptRepository.findTop10ByUserAliasOrderByIdDesc(userAlias);
        StatsUserAttemptDto statsUserAttemptDto = new StatsUserAttemptDto();
        statsUserAttemptDto.setAttempts(attempts);
        return statsUserAttemptDto;
    }
}