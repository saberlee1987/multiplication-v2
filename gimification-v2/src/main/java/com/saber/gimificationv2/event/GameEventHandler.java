package com.saber.gimificationv2.event;

import com.saber.gimificationv2.dto.ChallengeSolveEvent;
import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.services.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class GameEventHandler {

    private final GameService gameService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.gamification}")
    public void handleMultiplicationSolved(ChallengeSolveEvent event){
     log.info("Received event ChallengeSolveEvent ===> {}",event);
     try {
         ChallengeSolvedDto dto= new ChallengeSolvedDto();
         dto.setFactorA(event.getFactorA());
         dto.setFactorB(event.getFactorB());
         dto.setAttemptId(event.getAttemptId());
         dto.setCorrect(event.getCorrect());
         dto.setUserAlias(event.getUserAlias());
         dto.setUserId(event.getUserId());
        gameService.newAttemptForUser(dto);
     }catch (Exception ex){
         log.error("Error when trying to process ChallengeSolveEvent ===> {}",ex.getMessage());
         throw new AmqpRejectAndDontRequeueException(ex);
     }

    }
}
