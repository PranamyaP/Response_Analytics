package com.feedback.feedback_analytics.repository;

import com.feedback.feedback_analytics.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findBySessionId(Long sessionId);
    
    long countBySessionId(Long sessionId);

}
