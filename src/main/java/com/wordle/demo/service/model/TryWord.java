package com.wordle.demo.service.model;

import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.repository.entity.TryWordEntity;
import com.wordle.demo.service.WordService;

public record TryWord(Long id, Long gameId, Word word, Long tryNumber) {
    public TryWord(TryWordEntity entity, WordService wordService)
            throws WordNotFoundException {
        this(
                entity.getId(),
                entity.getGameId(),
                wordService.findWordById(entity.getWordId()),
                entity.getTryNumber()
        );
    }
}
