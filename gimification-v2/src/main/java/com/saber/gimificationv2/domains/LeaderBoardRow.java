package com.saber.gimificationv2.domains;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaderBoardRow {
    private Long userId;
    private Long totalScore;
    @With
    private List<String> badges;

    public LeaderBoardRow(final Long userId, final Long totalScore) {
        this.userId = userId;
        this.totalScore = totalScore;
        this.badges = List.of();
    }
}
