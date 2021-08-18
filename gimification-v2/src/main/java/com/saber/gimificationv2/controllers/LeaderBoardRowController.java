package com.saber.gimificationv2.controllers;

import com.saber.gimificationv2.domains.LeaderBoardRow;
import com.saber.gimificationv2.dto.LeaderBoardResponse;
import com.saber.gimificationv2.services.GameService;
import com.saber.gimificationv2.services.LeaderBoardService;
import com.saber.gimificationv2.services.impl.LeaderBoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/gamification")
@RequiredArgsConstructor
@Slf4j
public class LeaderBoardRowController {

    private final LeaderBoardService leaderBoardService;

    @GetMapping(value = "/leaders")
    public LeaderBoardResponse getLeaderBoardRows(){
        List<LeaderBoardRow> leaderBoardRows =  leaderBoardService.getCurrentLeaderBord();
        LeaderBoardResponse leaderBoardResponse = new LeaderBoardResponse();
        leaderBoardResponse.setLeaders(leaderBoardRows);
        log.info("leaderBoardResponse ===> {}" ,leaderBoardResponse);
        return leaderBoardResponse;
    }


}
