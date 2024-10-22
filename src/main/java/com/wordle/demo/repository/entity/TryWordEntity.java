package com.wordle.demo.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trywords")
public class TryWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "word_id")
    private Long wordId;

    @Column(name = "try_number")
    private Long tryNumber;

    public TryWordEntity() {
    }

    public TryWordEntity(Long gameId, Long wordId, Long tryNumber) {
        this.gameId = gameId;
        this.wordId = wordId;
        this.tryNumber = tryNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getWordId() {
        return wordId;
    }

    public void setWordId(Long wordId) {
        this.wordId = wordId;
    }

    public Long getTryNumber() {
        return tryNumber;
    }

    public void setTryNumber(Long tryNumber) {
        this.tryNumber = tryNumber;
    }
}
