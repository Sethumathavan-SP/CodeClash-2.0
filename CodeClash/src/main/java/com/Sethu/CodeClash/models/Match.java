package com.Sethu.CodeClash.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    private User user2;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Questions question;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "completed_at")
    private Long completedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Questions getQuestion() {
        return question;
    }

    public void setQuestion(Questions question) {
        this.question = question;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Long completedAt) {
        this.completedAt = completedAt;
    }
}

