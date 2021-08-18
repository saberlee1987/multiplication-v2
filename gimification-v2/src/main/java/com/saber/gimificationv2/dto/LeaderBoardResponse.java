package com.saber.gimificationv2.dto;

import com.saber.gimificationv2.domains.LeaderBoardRow;
import lombok.Data;

import java.util.List;

@Data
public class LeaderBoardResponse {
    private List<LeaderBoardRow> leaders;
}
