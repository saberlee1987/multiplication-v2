package com.saber.multiplication.multiplicationv2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeSolvedDto {
    private Long attemptId;
    private boolean correct;
    private Integer factorA;
    private Integer factorB;
    private Long userId;
    private String userAlias;
}