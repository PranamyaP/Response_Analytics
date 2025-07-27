package com.feedback.feedback_analytics.model;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;
    
    private String sectionName;
    private String questionText;
    private String questionType;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<ResponseAnswer> answers;
    
    public String getQuestionText() {
        return questionText;
    }

    public Long getId() {
        return id;
    }


    // Getters and setters
}
