package com.saber.multiplication.multiplicationv2.dto;

import lombok.Data;

import java.util.List;
@Data
public class StatsUserAttemptDto {
    private List<ChallengeAttempt> attempts;
}
