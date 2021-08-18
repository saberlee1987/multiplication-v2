package com.saber.gimificationv2.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ChallengeSolvedDto {
    @NotNull(message = "attemptId is Required")
    @Positive(message = "attemptId must be > 0")
    private Long attemptId;
    @NotNull(message = "correct is Required")
    private boolean correct;
    @NotNull(message = "factorA is Required")
    @Positive(message = "factorA must be > 0")
    @Max(value = 99,message = "factorA must be < 100")
    private Integer factorA;
    @NotNull(message = "Integer is Required")
    @Positive(message = "factorB must be > 0")
    @Max(value = 99,message = "factorB must be < 100")
    private Integer factorB;
    @NotNull(message = "userId is Required")
    @Positive(message = "userId must be > 0")
    private Long userId;
    @NotBlank(message = "userAlias is Required")
    private String userAlias;
}