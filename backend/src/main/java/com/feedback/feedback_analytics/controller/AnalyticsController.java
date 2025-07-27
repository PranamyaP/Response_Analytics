package com.feedback.feedback_analytics.controller;

import com.feedback.feedback_analytics.model.*;
import com.feedback.feedback_analytics.repository.*;
import com.feedback.feedback_analytics.service.FeedbackAnalyticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:3000")
public class AnalyticsController {

    @Autowired
    private FeedbackAnalyticsService feedbackService;

    // 🔹 Get all training sessions
    @GetMapping("/sessions")
    public List<Session> getAllSessions() {
        return feedbackService.getAllSessions();
    }

    // 🔹 Get questions by session ID
    @GetMapping("/sessions/{sessionId}/questions")
    public List<Question> getQuestions(@PathVariable Long sessionId) {
        return feedbackService.getQuestionsBySession(sessionId);
    }

    // 🔹 Get aggregated answers for charting
    @GetMapping("/sessions/{sessionId}/summary")
    public Map<String, Map<String, Long>> getSummary(@PathVariable Long sessionId) {
        return feedbackService.getAggregatedResponseByQuestion(sessionId);
    }
    @GetMapping("/sentiment/average/{sessionId}")
    public Map<String, Object> getAverageSentimentBySession(@PathVariable Long sessionId) {
        double avg = feedbackService.calculateAverageSentimentBySession(sessionId);

        Map<String, Object> result = new HashMap<>();
        result.put("averageSentiment", avg);
        result.put("category",
            avg >= 0.5 ? "Very Positive" :
            avg >= 0.2 ? "Positive" :
            avg >= -0.2 ? "Neutral" : "Negative");

        return result;
    }
    
    @GetMapping("/sessions/{sessionId}/responses/count")
    public Map<String, Object> getResponseCount(@PathVariable Long sessionId) {
        long count = feedbackService.getResponseCountBySession(sessionId);
        Map<String, Object> result = new HashMap<>();
        result.put("responseCount", count);
        return result;
    }

}
