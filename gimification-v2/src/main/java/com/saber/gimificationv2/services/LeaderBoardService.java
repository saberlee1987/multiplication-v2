package com.saber.gimificationv2.services;

import com.saber.gimificationv2.domains.LeaderBoardRow;

import java.util.List;

public interface LeaderBoardService {
    List<LeaderBoardRow> getCurrentLeaderBord();
}
