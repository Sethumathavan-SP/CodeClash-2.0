package com.Sethu.CodeClash.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
@CrossOrigin(origins = "*")
public class MatchController {

    @GetMapping("/{matchId}")
    public String getMatch(@PathVariable String matchId) {
        return "Match: " + matchId;
    }

    @PostMapping("/submit")
    public String submitCode(@RequestBody String code) {
        return "Code submitted";
    }
}

