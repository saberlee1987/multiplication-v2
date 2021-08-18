package com.saber.gimificationv2.services.badgeprocessors;

import com.saber.gimificationv2.domains.BadgeType;
import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.domains.ScoreCard;

import java.util.List;
import java.util.Optional;

public interface BadgeProcessor {
    Optional<BadgeType> processForOptionalBadge(Integer currentScore,
                                                List<ScoreCard> scoreCards,
                                                ChallengeSolvedDto solvedDto);

    BadgeType badgeType();
}
