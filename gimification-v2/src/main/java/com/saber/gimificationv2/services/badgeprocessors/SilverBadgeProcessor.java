package com.saber.gimificationv2.services.badgeprocessors;

import com.saber.gimificationv2.domains.BadgeType;
import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.domains.ScoreCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SilverBadgeProcessor implements BadgeProcessor {

    @Value(value = "${service.badgeProcessor.silverNumber}")
    private Integer silverNumber;

    @Override
    public Optional<BadgeType> processForOptionalBadge(Integer currentScore, List<ScoreCard> scoreCards, ChallengeSolvedDto solvedDto) {

        return currentScore > silverNumber ?
                Optional.of(BadgeType.SILVER) :
                Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.SILVER;
    }
}
