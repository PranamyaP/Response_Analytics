package com.feedback.feedback_analytics.model;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;
    
    private String respondentName;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime submittedAt;
    private Double rating;
    
    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
    private List<ResponseAnswer> answers;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    // Getters and setters
}
