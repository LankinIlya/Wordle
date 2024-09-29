package com.wordle.demo.service;

import com.wordle.demo.exception.IncorrectGuessException;
import com.wordle.demo.exception.WordNotFoundException;

import java.util.List;


public interface GameService {
    public void newGame();

    public List<Integer> tryWord(String guess)
            throws WordNotFoundException, IncorrectGuessException;
}
