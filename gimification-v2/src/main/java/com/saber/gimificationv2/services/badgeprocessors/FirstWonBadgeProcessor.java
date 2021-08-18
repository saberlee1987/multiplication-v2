package com.saber.gimificationv2.services.badgeprocessors;

import com.saber.gimificationv2.domains.BadgeType;
import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.domains.ScoreCard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FirstWonBadgeProcessor implements BadgeProcessor {
    @Override
    public Optional<BadgeType> processForOptionalBadge(Integer currentScore,
                                                       List<ScoreCard> scoreCards,
                                                       ChallengeSolvedDto solvedDto) {
        return scoreCards.size() == 1 ?
                Optional.of(BadgeType.FIRST_WON) :
                Optional.empty();
    }

    @Override
    public BadgeType badgeType() {
        return BadgeType.FIRST_WON;
    }
}
