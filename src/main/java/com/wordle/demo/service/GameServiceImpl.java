package com.wordle.demo.service;

import com.wordle.demo.exception.IncorrectGuessException;
import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.repository.WordRepository;
import com.wordle.demo.repository.entity.WordEntity;
import com.wordle.demo.service.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService{
    private Word word;

    private WordRepository wordRepository;

    public GameServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void newGame() {
        word = chooseRandomWord();
    }

    public List<Integer> tryWord(String guess)
            throws WordNotFoundException, IncorrectGuessException {
        if(guess.length() != word.text().length())
            throw new IncorrectGuessException();

        Optional<WordEntity> entity = wordRepository.findByText(guess);
        if(entity.isEmpty())
            throw new IncorrectGuessException();

        List<Integer> result = new ArrayList<>(guess.length());

        for(int i = 0; i < word.text().length(); i++) {
            result.add(0);
            if(word.text().charAt(i) == guess.charAt(i)) {
                result.set(i, 2);
            } else {
                for(int j = 0; j < word.text().length(); j++) {
                    if(word.text().charAt(j) == guess.charAt(i)) {
                        result.set(i, 1);
                        break;
                    }
                }
            }
        }

        return result;
    }

    private Word chooseRandomWord() {
        long amount = wordRepository.count();
        System.out.println("Amount of words in repository: " + amount);
        int index = new Random().nextInt((int)amount);
        Pageable pageable = PageRequest.of(index, 1);
        List<WordEntity> result = wordRepository.findAll(pageable).getContent();

        try {
            WordEntity wordEntity = result.get(0);
            return new Word(wordEntity.getId(), wordEntity.getText());
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
