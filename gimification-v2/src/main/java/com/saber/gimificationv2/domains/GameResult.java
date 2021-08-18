package com.saber.gimificationv2.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResult {
    private Integer score;
    private List<BadgeType> badges;

}
