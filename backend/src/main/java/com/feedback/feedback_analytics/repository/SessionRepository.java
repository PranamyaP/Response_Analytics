package com.feedback.feedback_analytics.repository;

import com.feedback.feedback_analytics.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
