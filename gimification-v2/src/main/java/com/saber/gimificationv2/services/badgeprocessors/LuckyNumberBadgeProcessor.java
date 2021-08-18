package com.saber.gimificationv2.services.badgeprocessors;

import com.saber.gimificationv2.domains.BadgeType;
import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.domains.ScoreCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LuckyNumberBadgeProcessor implements BadgeProcessor {
    @Value(value = "${service.badgeProcessor.luckyFactor}")
    private Integer luckyFactor;

    @Override
    public Optional<BadgeType> processForOptionalBadge(Integer currentScore, List<ScoreCard> scoreCards,
                                                       ChallengeSolvedDto solvedDto) {
        return solvedDto.getFactorA().equals(luckyFactor) ||
                solvedDto.getFactorB().equals(luckyFactor) ?
                Optional.of(BadgeType.LUCK_NUMBER) :
                Optional.empty();

    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.LUCK_NUMBER;
    }
}
