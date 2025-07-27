package com.feedback.feedback_analytics.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String trainerName;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Question> questions;
    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
