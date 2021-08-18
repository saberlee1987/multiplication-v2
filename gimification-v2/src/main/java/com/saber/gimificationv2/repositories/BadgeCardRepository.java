package com.saber.gimificationv2.repositories;

import com.saber.gimificationv2.domains.BadgeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeCardRepository extends JpaRepository<BadgeCard,Long> {

    List<BadgeCard> findByUserIdOrderByBadgeTimeStampDesc(Long userId);
}
