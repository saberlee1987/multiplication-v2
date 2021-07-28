package com.saber.multiplication.multiplicationv2.services.impl;

import com.saber.multiplication.multiplicationv2.dto.Challenge;
import com.saber.multiplication.multiplicationv2.services.ChallengeGeneratorService;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class ChallengeGeneratorServiceImpl implements ChallengeGeneratorService {

    private Random random;
    private static final Integer MIN_FACTOR = 11;
    private static final Integer MAX_FACTOR = 100;

    public ChallengeGeneratorServiceImpl() {
        random = new Random();
    }

    public ChallengeGeneratorServiceImpl(Random random) {
        this.random = random;
    }

    private Integer next(){
        return random.nextInt(MAX_FACTOR - MIN_FACTOR) + MIN_FACTOR;
    }

    @Override
    public Challenge randomChallenge() {
        return new Challenge(next(),next());
    }
}
