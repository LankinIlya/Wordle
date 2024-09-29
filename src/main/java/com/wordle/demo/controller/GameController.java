package com.wordle.demo.controller;

import com.wordle.demo.controller.dto.TryWordDto;
import com.wordle.demo.controller.dto.TryWordResponseDto;
import com.wordle.demo.exception.IncorrectGuessException;
import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/new_game")
    public String newGame() {
        gameService.newGame();
        return "ok";
    }

    @PostMapping("/try_word")
    public TryWordResponseDto tryWord(@RequestBody TryWordDto tryWordDto) {
        try {
            return new TryWordResponseDto(
                gameService.tryWord(tryWordDto.word())
            );
        }
        catch (WordNotFoundException ex) {
            return new TryWordResponseDto(1);
        }
        catch (IncorrectGuessException ex) {
            return new TryWordResponseDto(1);
        }

    }
}
