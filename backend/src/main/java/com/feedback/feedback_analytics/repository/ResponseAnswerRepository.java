package com.feedback.feedback_analytics.repository;

import com.feedback.feedback_analytics.model.ResponseAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ResponseAnswerRepository extends JpaRepository<ResponseAnswer, Long> {
    List<ResponseAnswer> findByResponseId(Long responseId);
    List<ResponseAnswer> findByQuestionId(Long questionId);
    
    @Query("SELECT ra FROM ResponseAnswer ra WHERE ra.question.session.id = :sessionId")
    List<ResponseAnswer> findAnswersBySessionId(@Param("sessionId") Long sessionId);
    
    @Query("SELECT COUNT(ra) FROM ResponseAnswer ra WHERE ra.question.session.id = :sessionId")
    long countBySessionId(@Param("sessionId") Long sessionId);

}
    
