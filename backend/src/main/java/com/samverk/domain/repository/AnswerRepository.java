package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samverk.domain.entity.Answer;

import java.util.UUID;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    // Find answers related to a specific request
    List<Answer> findByRequestId(UUID requestId);
}
