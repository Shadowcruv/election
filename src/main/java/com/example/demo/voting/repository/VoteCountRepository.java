package com.example.demo.voting.repository;

import com.example.demo.voting.entity.VoteCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteCountRepository extends JpaRepository<VoteCount, Long> {


}

