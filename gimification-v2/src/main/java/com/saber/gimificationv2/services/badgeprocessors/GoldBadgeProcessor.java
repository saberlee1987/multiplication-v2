package com.saber.gimificationv2.services.badgeprocessors;

import com.saber.gimificationv2.domains.BadgeType;
import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.domains.ScoreCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GoldBadgeProcessor implements BadgeProcessor {
    @Value(value = "${service.badgeProcessor.goldNumber}")
    private Integer goldNumber;
    @Override
    public Optional<BadgeType> processForOptionalBadge(Integer currentScore,
                                                       List<ScoreCard> scoreCards,
                                                       ChallengeSolvedDto solvedDto) {
        return currentScore > goldNumber ?
                Optional.of(BadgeType.GOLD):
                Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.GOLD;
    }
}
