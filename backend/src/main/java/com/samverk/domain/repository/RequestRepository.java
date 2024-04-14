package com.samverk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.samverk.domain.model.Request;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    // Find requests by the user who made them
    List<Request> findByUserId(Long userId);
}
