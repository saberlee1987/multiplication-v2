package com.saber.gimificationv2.services.impl;

import com.saber.gimificationv2.domains.*;
import com.saber.gimificationv2.dto.ChallengeSolvedDto;
import com.saber.gimificationv2.repositories.BadgeCardRepository;
import com.saber.gimificationv2.repositories.ScoreCardRepository;
import com.saber.gimificationv2.services.GameService;
import com.saber.gimificationv2.services.badgeprocessors.BadgeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    private ScoreCardRepository scoreCardRepository;
    private BadgeCardRepository badgeCardRepository;
    private List<BadgeProcessor> badgeProcessorList;

    public GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository, List<BadgeProcessor> badgeProcessorList) {
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
        this.badgeProcessorList = badgeProcessorList;
    }

    @Override
    public GameResult newAttemptForUser(ChallengeSolvedDto solvedDto) {
        if (solvedDto.isCorrect()) {
            ScoreCard scoreCard = new ScoreCard(solvedDto.getUserId(), solvedDto.getAttemptId());
            scoreCardRepository.save(scoreCard);
            log.info("User {} scored {} points for attempt id {}",
                    solvedDto.getUserAlias(),
                    scoreCard.getScore(),
                    solvedDto.getAttemptId());
            List<BadgeCard> badgeCards = processForBadges(solvedDto);
            return new GameResult(scoreCard.getScore(),
                    badgeCards.stream().map(BadgeCard::getBadgeType)
                            .collect(Collectors.toList()));
        } else {
            log.info("Attempt is {} is not correct. User {} does not got score ",
                    solvedDto.getAttemptId(), solvedDto.getUserAlias());
            return new GameResult(0, List.of());
        }
    }

    private List<BadgeCard> processForBadges(ChallengeSolvedDto solvedDto) {
        Optional<Integer> optionalTotalScore = scoreCardRepository.getTotalScoreForUser(solvedDto.getUserId());
        if (optionalTotalScore.isEmpty())
            return Collections.emptyList();
        Integer totalScore = optionalTotalScore.get();

        List<ScoreCard> scoreCardList = scoreCardRepository
                .findByUserIdOrderByScoreTimeStampDesc(solvedDto.getUserId());

        Set<BadgeType> alreadyGotBadges = badgeCardRepository
                .findByUserIdOrderByBadgeTimeStampDesc(solvedDto.getUserId())
                .stream()
                .map(BadgeCard::getBadgeType)
                .collect(Collectors.toSet());

        List<BadgeCard> newBadgeCards = badgeProcessorList.stream()
                .filter(badgeProcessor -> !alreadyGotBadges.contains(badgeProcessor.badgeType()))
                .map(badgeProcessor ->
                        badgeProcessor.processForOptionalBadge(totalScore, scoreCardList, solvedDto))
                .flatMap(Optional::stream)
                .map(badgeType -> new BadgeCard(solvedDto.getUserId(), badgeType))
                .collect(Collectors.toList());

        badgeCardRepository.saveAll(newBadgeCards);

        return newBadgeCards;
    }
}