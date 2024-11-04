package com.example.demo.voting.service;

import com.example.demo.voting.entity.VoteCount;
import com.example.demo.voting.repository.VoteCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotingService {
    @Autowired
    private VoteCountRepository voteCountRepository;

    public VoteCount getVoteCount() {
        return voteCountRepository.findById(1L)
                .orElse(new VoteCount()); // Load existing or create new record
    }

    public void voteFor(String candidate) {
        VoteCount voteCount = getVoteCount();
        voteCount.setTotalVotes(voteCount.getTotalVotes() + 1);

        if ("Trump".equalsIgnoreCase(candidate)) {
            voteCount.setTrumpVotes(voteCount.getTrumpVotes() + 1);
        } else if ("Kamala".equalsIgnoreCase(candidate)) {
            voteCount.setKamalaVotes(voteCount.getKamalaVotes() + 1);
        }

        voteCountRepository.save(voteCount);
    }

    public VoteCount getCurrentVoteCounts() {
        // Retrieve votes for each candidate from the database
        int trumpVotes =  voteCountRepository.findById(1L)
                .orElse(new VoteCount()).getTrumpVotes();
        int kamalaVotes =  voteCountRepository.findById(1L)
                .orElse(new VoteCount()).getKamalaVotes(); // Load existing or create new record

        // Calculate the total votes
        int totalVotes = trumpVotes + kamalaVotes;

        // Create and return a VoteCount object with the current vote counts
        return VoteCount.builder().totalVotes(totalVotes).trumpVotes(trumpVotes).kamalaVotes(kamalaVotes).build();
    }
}

