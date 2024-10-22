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

    public Game(Long id, Long userId, Boolean isActive, Boolean isWon, Word word) {
        this.id = id;
        this.userId = userId;
        this.isActive = isActive;
        this.isWon = isWon;
        this.word = word;
    }

    public Game(GameEntity gameEntity, WordService wordService) {
<<<<<<< HEAD
        System.out.println("Game constructor 1");
        this.id = gameEntity.getId();
        System.out.println("Game constructor 2");
        this.userId = gameEntity.getUserId();
        System.out.println("Game constructor 3");
        this.isActive = gameEntity.getActive();
        System.out.println("Game constructor 4");
        this.isWon = gameEntity.getWon();
        System.out.println("Game constructor 4");
        try {
            System.out.println("Game constructor 5");
            this.word = wordService.findWordById(gameEntity.getId());
            System.out.println("Game constructor 6");
        } catch (WordNotFoundException e) {
            System.out.println("Game from gameEntity: Word not found");
            throw new RuntimeException(e);
        }
        System.out.println("Game constructor end");
=======
        this.id = gameEntity.getId();
        this.userId = gameEntity.getUserId();
        this.isActive = gameEntity.getActive();
        try {
            this.word = wordService.findWordById(gameEntity.getId());
        } catch (WordNotFoundException e) {
            throw new RuntimeException(e);
        }
>>>>>>> 3ca1effc5be888ace247b05dfbf07bd25ab404d7
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
