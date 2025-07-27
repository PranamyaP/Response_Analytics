package com.feedback.feedback_analytics.service;

import com.feedback.feedback_analytics.model.SentimentRequest;
import com.feedback.feedback_analytics.model.SentimentResult;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SentimentService {

    private final String SENTIMENT_API_URL = "http://localhost:5000/api/sentiment";

    public SentimentResult analyzeSentiment(String text) {
        RestTemplate restTemplate = new RestTemplate();
        SentimentRequest request = new SentimentRequest(text);

        return restTemplate.postForObject(SENTIMENT_API_URL, request, SentimentResult.class);
    }
    
    public SentimentResult analyzeSentimentBatch(List<String> texts) {
        double totalScore = 0.0;
        int positive = 0, negative = 0, neutral = 0;

        for (String text : texts) {
            SentimentResult result = analyzeSentiment(text);
            double score = result.getScore();
            totalScore += score;

            if (score > 0.1) positive++;
            else if (score < -0.1) negative++;
            else neutral++;
        }

        double avg = texts.size() > 0 ? totalScore / texts.size() : 0.0;
        String polarity = avg > 0.1 ? "positive" : (avg < -0.1 ? "negative" : "neutral");

        return new SentimentResult(polarity, avg);
    }

}
