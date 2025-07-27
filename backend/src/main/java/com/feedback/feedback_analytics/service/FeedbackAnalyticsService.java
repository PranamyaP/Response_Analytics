package com.feedback.feedback_analytics.service;

import com.feedback.feedback_analytics.model.*;
import com.feedback.feedback_analytics.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeedbackAnalyticsService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @Autowired
    private ResponseAnswerRepository responseAnswerRepository;

    @Autowired
    private SentimentService sentimentService;
    
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public List<Question> getQuestionsBySession(Long sessionId) {
        return questionRepository.findBySessionId(sessionId);
    }

    public Map<String, Map<String, Long>> getAggregatedResponseByQuestion(Long sessionId) {
        List<Question> questions = questionRepository.findBySessionId(sessionId);
        List<Response> responses = responseRepository.findBySessionId(sessionId);


        Map<String, Map<String, Long>> questionStats = new LinkedHashMap<>();

        for (Question q : questions) {
            List<ResponseAnswer> answers = responseAnswerRepository.findByQuestionId(q.getId());

            Map<String, Long> counts = answers.stream()
                    .collect(Collectors.groupingBy(ResponseAnswer::getAnswerText, Collectors.counting()));

            questionStats.put(q.getQuestionText(), counts);
        }

        return questionStats;
    }
    
    public Map<String, Object> getDashboardSummary() {
        List<Session> sessions = sessionRepository.findAll();
        List<Response> responses = responseRepository.findAll();

        double avgRating = responses.stream()
            .filter(r -> r.getRating() != null)
            .mapToDouble(Response::getRating)
            .average()
            .orElse(0.0);

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalSessions", sessions.size());
        summary.put("totalResponses", responses.size());
        summary.put("averageRating", avgRating);

        return summary;
    }

    public double calculateAverageSentiment() {
        List<ResponseAnswer> allAnswers = responseAnswerRepository.findAll();

        double totalScore = 0.0;
        int count = 0;

        for (ResponseAnswer answer : allAnswers) {
            String text = answer.getAnswerText();
            if (text != null && !text.isEmpty()) {
                try {
                    SentimentResult result = sentimentService.analyzeSentiment(text);
                    double score = result.getScore();
                    totalScore += score;
                    count++;
                } catch (Exception e) {
                    System.out.println("Sentiment API failed for: " + text);
                }
            }
        }

        return count > 0 ? totalScore / count : 0.0;
    }
    public double calculateAverageSentimentBySession(Long sessionId) {
        List<ResponseAnswer> answers = responseAnswerRepository.findAnswersBySessionId(sessionId);

        List<String> texts = answers.stream()
            .filter(a -> a.getAnswerText() != null && !a.getAnswerText().isEmpty())
            .map(ResponseAnswer::getAnswerText)
            .collect(Collectors.toList());

        double total = 0;
        int count = 0;

        for (String text : texts) {
            try {
                SentimentResult result = sentimentService.analyzeSentiment(text);
                total += result.getScore();
                count++;
            } catch (Exception e) {
                e.printStackTrace(); // handle gracefully
            }
        }

        return count > 0 ? total / count : 0;
    }

    public long getResponseCountBySession(Long sessionId) {
        return responseRepository.countBySessionId(sessionId);
    }


    
    
}
