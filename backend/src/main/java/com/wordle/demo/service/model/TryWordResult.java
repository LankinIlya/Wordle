package com.wordle.demo.service.model;

import java.util.List;

public record TryWordResult(List<Integer> result,
                            boolean isActive,
                            boolean isWon,
                            String answer) {
    public TryWordResult(List<Integer> res) {
        this(res, true, false, null);
    }
}
