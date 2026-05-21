package com.Sethu.CodeClash.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "test_cases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Questions question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String input;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String expectedOutput;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}

