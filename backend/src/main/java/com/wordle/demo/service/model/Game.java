package com.wordle.demo.service.model;

import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.repository.WordRepository;
import com.wordle.demo.repository.entity.GameEntity;
import com.wordle.demo.repository.entity.WordEntity;
import com.wordle.demo.service.WordService;
import jakarta.persistence.Column;

import java.util.Optional;

public class Game {
    private Long id;

    private Long userId;

    private Boolean isActive;

    private Boolean isWon;

    private Word word;

    public Game() {
    }

    public Game(Long id,
                Long userId,
                Boolean isActive,
                Boolean isWon,
                Word word) {
        this.id = id;
        this.userId = userId;
        this.isActive = isActive;
        this.isWon = isWon;
        this.word = word;
    }

    public Game(GameEntity gameEntity, WordService wordService) {
        this.id = gameEntity.getId();
        this.userId = gameEntity.getUserId();
        this.isActive = gameEntity.getActive();
        this.isWon = gameEntity.getWon();
        try {
            this.word = wordService.findWordById(gameEntity.getWordId());
        } catch (WordNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
