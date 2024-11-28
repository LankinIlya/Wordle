package com.wordle.demo.controller.dto;

public record UserDto(String login, String password,
                      Integer games, Integer wins) {
}
