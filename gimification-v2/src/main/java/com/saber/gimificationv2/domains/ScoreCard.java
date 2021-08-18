package com.saber.gimificationv2.domains;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class ScoreCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private Long userId;
    private Long attemptId;
    @EqualsAndHashCode.Exclude
    private Long scoreTimeStamp;
    private Integer score;

    public static final Integer DEFAULT_SCORE = 10;

    public ScoreCard(Long userId, Long attemptId) {
        this.userId = userId;
        this.attemptId = attemptId;
        score = DEFAULT_SCORE;
        scoreTimeStamp = System.currentTimeMillis();
    }
}
