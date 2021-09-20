package com.saber.multiplication.multiplicationv2.event;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import com.saber.multiplication.multiplicationv2.dto.ChallengeSolveEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChallengeEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Value(value = "${spring.rabbitmq.exchange.attempts}")
    private String challengeTopicExchange;

    public void challengeSolved(ChallengeAttempt attempt) {

        ChallengeSolveEvent event = buildEvent(attempt);
        String routingKey = String.format("attempt.%s"
                , (event.getCorrect() ? "correct" : "wrong")
        );
        log.info("send event with body ===> {} with routingKey {}", event,routingKey);
       rabbitTemplate.convertAndSend(challengeTopicExchange,routingKey,event);
    }

    private ChallengeSolveEvent buildEvent(ChallengeAttempt attempt) {
        ChallengeSolveEvent event = new ChallengeSolveEvent();
        event.setCorrect(attempt.getCorrect());
        event.setFactorA(attempt.getFactorA());
        event.setFactorB(attempt.getFactorB());
        event.setAttemptId(attempt.getId());
        event.setUserId(attempt.getUser().getId());
        event.setUserAlias(attempt.getUser().getAlias());
        return event;
    }

}
