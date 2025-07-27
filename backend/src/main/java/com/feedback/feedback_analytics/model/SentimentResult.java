package com.feedback.feedback_analytics.model;

public class SentimentResult {
    private String polarity;
    private double score;

    public SentimentResult() {}

    public SentimentResult(String polarity, double score) {
        this.polarity = polarity;
        this.score = score;
    }

    public String getPolarity() {
        return polarity;
    }

    public void setPolarity(String polarity) {
        this.polarity = polarity;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
