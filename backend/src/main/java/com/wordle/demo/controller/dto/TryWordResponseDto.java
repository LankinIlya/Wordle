package com.wordle.demo.controller.dto;

import com.wordle.demo.service.model.TryWordResult;

import java.util.List;

public record TryWordResponseDto(int status,
                                 List<Integer> result,
                                 boolean isActive,
                                 boolean isWon,
                                 String answer) {
    public TryWordResponseDto(int status) {
        this(status, null, false, false, null);
    }

    public TryWordResponseDto(TryWordResult tryWordResult) {
        this(0,
                tryWordResult.result(),
                tryWordResult.isActive(),
                tryWordResult.isWon(),
                tryWordResult.answer());
    }
}
