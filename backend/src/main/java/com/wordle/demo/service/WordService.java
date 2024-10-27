package com.wordle.demo.service;


import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.service.model.Word;

public interface WordService {
    Word findWordById(Long id) throws WordNotFoundException;
}
