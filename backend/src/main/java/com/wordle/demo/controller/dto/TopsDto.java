package com.wordle.demo.controller.dto;

import java.util.List;

public record TopsDto(List<UserRating> topWins, List<UserRating> topRatio) {


}
