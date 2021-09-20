package com.saber.gimificationv2.dto;

import lombok.Data;

@Data
public class ChallengeSolveEvent {
    private String userAlias;
    private Integer factorA;
    private Integer factorB;
    private long attemptId;
    private long userId;
    private Boolean correct;
}
