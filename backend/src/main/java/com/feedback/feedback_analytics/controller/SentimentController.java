package com.feedback.feedback_analytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.feedback.feedback_analytics.model.SentimentRequest;
import com.feedback.feedback_analytics.model.SentimentResult;
import com.feedback.feedback_analytics.service.SentimentService;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class SentimentController {

    @Autowired
    private SentimentService sentimentService;

    @PostMapping("/sentiment")
    public SentimentResult getSentiment(@RequestBody SentimentRequest request) {
    	return sentimentService.analyzeSentiment(request.getText());

    }
    

}

