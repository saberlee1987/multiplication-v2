package com.saber.multiplication.multiplicationv2.client;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeSolvedDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GamificationServiceClient {

    @Value(value = "${service.gamification.url}")
    private String gamificationUrl;
    @Value(value = "${service.gamification.port}")
    private Integer gamificationPort;
    @Value(value = "${service.gamification.path}")
    private String gamificationPath;

    @Autowired
    private RestTemplate restTemplate;

    public boolean sendAttempt(ChallengeAttempt attempt) {
        try {

            ChallengeSolvedDto solvedDto = new ChallengeSolvedDto(
                    attempt.getId(),
                    attempt.getCorrect(),
                    attempt.getFactorA(),
                    attempt.getFactorB(),
                    attempt.getUser().getId(),
                    attempt.getUser().getAlias()
            );
            String url = String.format("%s:%d%s", gamificationUrl, gamificationPort, gamificationPath);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, solvedDto, String.class);
            log.info("Gamification Service response : {} ", responseEntity.getStatusCode());
            return responseEntity.getStatusCode().is2xxSuccessful();
        } catch (Exception ex) {
            log.error("There was a problem sending the attempt {} ", ex.getMessage());
            return false;
        }
    }
}
