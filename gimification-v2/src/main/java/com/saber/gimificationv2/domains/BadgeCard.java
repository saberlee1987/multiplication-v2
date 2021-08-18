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
public class BadgeCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;
    private Long userId;
    @EqualsAndHashCode.Exclude
    private Long badgeTimeStamp;
    private BadgeType badgeType;

    public BadgeCard(Long userId, BadgeType badgeType) {
        this.userId = userId;
        this.badgeType = badgeType;
    }
}