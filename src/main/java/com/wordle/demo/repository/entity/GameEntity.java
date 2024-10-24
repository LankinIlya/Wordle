package com.wordle.demo.repository.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_won")
    private Boolean isWon;

    @Column(name = "word_id")
    private Long wordId;

    public GameEntity() {
        userId = 0L;
        isActive = true;
        isWon = false;
        wordId = 0L;
    }

    public GameEntity(Long userId,
                      Boolean isActive,
                      Boolean isWon,
                      Long wordId) {
        this.userId = userId;
        this.isActive = isActive;
        this.isWon = isWon;
        this.wordId = wordId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getWon() {
        return isWon;
    }

    public void setWon(Boolean won) {
        isWon = won;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }
}
