package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.samverk.domain.model.Answer;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // Find answers related to a specific request
    List<Answer> findByRequestId(Long requestId);
}
