package com.wordle.demo.controller.dto;

import com.wordle.demo.exception.GameNotFoundException;
import com.wordle.demo.service.GameService;
import com.wordle.demo.service.WordService;
import com.wordle.demo.service.model.Game;
import com.wordle.demo.service.model.TryWord;

import java.util.ArrayList;
import java.util.List;

public class GameDto {
    private Long id;
    private List<String> words;
    private List<List<Integer>> results;
    private Boolean isActive;
    private Boolean isWon;
    private String answer;

    public GameDto(Game game, GameService gameService, WordService wordService)
            throws GameNotFoundException {
        this.id = game.getId();
        this.words = new ArrayList<>();
        this.results = new ArrayList<>();
        this.isActive = game.getActive();
        this.isWon = game.getWon();

        if(this.isActive)
            this.answer = game.getWord().text();
        else
            this.answer = null;

        List<TryWord> tryWords = gameService.getTryWords(game.getId());
        for(TryWord tryWord : tryWords) {
            this.words.add(tryWord.word().text());
        }

        for(String word : this.words) {
            this.results.add(
                    gameService.getResult(game.getWord().text(), word)
            );
        }
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public List<List<Integer>> getResults() {
        return results;
    }

    public void setResults(List<List<Integer>> results) {
        this.results = results;
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
