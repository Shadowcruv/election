package com.example.demo.voting.controller;


import com.example.demo.voting.entity.VoteCount;
import com.example.demo.voting.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private VotingService votingService;

    @GetMapping
    public String getVotePage(Model model) {
        VoteCount voteCount = votingService.getVoteCount();
        model.addAttribute("voteCount", voteCount);
        return "votingg"; // HTML template name
    }

    @GetMapping("/vote/counts")
    @ResponseBody
    public VoteCount getVoteCounts() {
        // Fetch the current vote counts from your data source
        VoteCount currentCounts = votingService.getCurrentVoteCounts();
        return currentCounts; // Assuming VoteCount is a POJO with totalVotes, trumpVotes, and kamalaVotes
    }

    @PostMapping("/cast")
    @ResponseBody
    public ResponseEntity<Void> castVote(@RequestParam String candidate) {
        votingService.voteFor(candidate);
        return ResponseEntity.ok().build();
    }
}

