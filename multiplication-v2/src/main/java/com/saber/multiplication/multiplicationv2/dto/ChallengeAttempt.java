package com.saber.multiplication.multiplicationv2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttempt {
    private Long id;
    private User user;
    private Integer factorA;
    private Integer factorB;
    private Integer resultAttempt;
    private Boolean correct;
}
