package com.wordle.demo.controller.dto;

import java.util.List;

public record TryWordResponseDto(int status, List<Integer> result) {
    public TryWordResponseDto(int status) { this(status, null); }
    public TryWordResponseDto(List<Integer> result) { this(0, result); }
}
