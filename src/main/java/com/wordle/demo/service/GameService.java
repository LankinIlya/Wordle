package com.wordle.demo.service;

import com.wordle.demo.exception.GameAlreadyFinishedException;
import com.wordle.demo.exception.GameNotFoundException;
import com.wordle.demo.exception.IncorrectGuessException;
import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.service.model.Game;
import com.wordle.demo.service.model.TryWord;

import java.util.List;


public interface GameService {
    Game newGame(Long userId);

    List<Integer> tryWord(Long gameId, String guess)
            throws WordNotFoundException,
                   IncorrectGuessException,
                   GameNotFoundException,
                   GameAlreadyFinishedException;

    Game getGameByUser(Long userId);

    Game getGameById(Long gameId, Long userId);

    List<Integer> getResult(String word, String guess);

    List<TryWord> getTryWords(Long gameId);
}
