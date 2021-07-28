package com.saber.multiplication.multiplicationv2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChallengeAttemptDto implements Serializable {
    @NotNull(message = "factorA is Required")
    @Min(value = 1,message = "minimum must be > 1")
    @Max(value = 99,message = "maximum must be < 99")
    private Integer factorA;
    @NotNull(message = "factorB is Required")
    @Min(value = 1,message = "minimum must be > 1")
    @Max(value = 99,message = "maximum must be < 99")
    private Integer factorB;
    @NotBlank(message = "userAlias is Required")
    private String userAlias;
    @NotNull(message = "guess is Required")
    @Positive(message = "guess must be > 0")
    private Integer guess;
}
