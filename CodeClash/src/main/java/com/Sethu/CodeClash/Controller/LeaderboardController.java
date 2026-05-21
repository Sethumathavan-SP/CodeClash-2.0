package com.Sethu.CodeClash.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {

    @GetMapping("/top")
    public String getTopPlayers() {
        return "Top players";
    }

    @GetMapping("/user/{userId}")
    public String getUserRank(@PathVariable String userId) {
        return "User rank";
    }
}

