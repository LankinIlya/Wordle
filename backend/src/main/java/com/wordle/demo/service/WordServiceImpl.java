package com.wordle.demo.service;

import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.repository.WordRepository;
import com.wordle.demo.repository.entity.WordEntity;
import com.wordle.demo.service.model.Word;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public Word findWordById(Long id) throws WordNotFoundException {
        Optional<WordEntity> wordEntity = wordRepository.findById(id);
        if(wordEntity.isEmpty())
            throw new WordNotFoundException();
        return new Word(wordEntity.get().getId(), wordEntity.get().getText());
    }
}
