package com.saber.gimificationv2.services.impl;

import com.saber.gimificationv2.domains.LeaderBoardRow;
import com.saber.gimificationv2.repositories.BadgeCardRepository;
import com.saber.gimificationv2.repositories.ScoreCardRepository;
import com.saber.gimificationv2.services.LeaderBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeaderBoardServiceImpl implements LeaderBoardService {

    private final ScoreCardRepository scoreCardRepository;
    private final BadgeCardRepository badgeCardRepository;
    @Override
    public List<LeaderBoardRow> getCurrentLeaderBord() {

        List<LeaderBoardRow> screenOnly = scoreCardRepository.findFirst10();

        log.info("screenOnly ====> {}",screenOnly);
        return screenOnly.stream()
                .map(row->{
                   List<String> badges = badgeCardRepository.
                           findByUserIdOrderByBadgeTimeStampDesc(row.getUserId())
                           .stream()
                           .map(b -> b.getBadgeType().getDescription())
                           .collect(Collectors.toList());

                   return row.withBadges(badges);
                }).collect(Collectors.toList());
    }
}
