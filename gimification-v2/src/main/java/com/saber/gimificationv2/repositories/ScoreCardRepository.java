package com.saber.gimificationv2.repositories;

import com.saber.gimificationv2.domains.LeaderBoardRow;
import com.saber.gimificationv2.domains.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreCardRepository extends JpaRepository<ScoreCard,Long> {
    @Query(value = "select sum(s.score) from ScoreCard s where s.userId=:userId group by s.userId")
    Optional<Integer> getTotalScoreForUser(@Param(value = "userId") Long userId);

    @Query(value = "select new com.saber.gimificationv2.domains.LeaderBoardRow(s.userId,sum(s.score)) from ScoreCard s group by s.userId order by sum(s.score) desc ")
    List<LeaderBoardRow> findFirst10();

    List<ScoreCard> findByUserIdOrderByScoreTimeStampDesc(Long userId);
}
