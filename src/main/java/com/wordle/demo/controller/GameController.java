package com.wordle.demo.controller;

import com.wordle.demo.controller.dto.GameDto;
import com.wordle.demo.controller.dto.TryWordDto;
import com.wordle.demo.controller.dto.TryWordResponseDto;
import com.wordle.demo.exception.GameAlreadyFinishedException;
import com.wordle.demo.exception.GameNotFoundException;
import com.wordle.demo.exception.IncorrectGuessException;
import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.service.GameService;
import com.wordle.demo.service.WordService;
import com.wordle.demo.service.model.Game;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final WordService wordService;

    public GameController(GameService gameService, WordService wordService) {
        this.gameService = gameService;
        this.wordService = wordService;
    }

    @GetMapping("/new_game")
    public String newGame() {
        Long userId = 1L;
        gameService.newGame(userId);
        return "ok";
    }

    @PostMapping("/try_word")
    public TryWordResponseDto tryWord(@RequestBody TryWordDto tryWordDto) {
        try {
            return new TryWordResponseDto(
                gameService.tryWord(tryWordDto.gameId(), tryWordDto.word())
            );
        }
        catch (WordNotFoundException ex) {
            return new TryWordResponseDto(1);
        }
        catch (IncorrectGuessException ex) {
            return new TryWordResponseDto(1);
        } catch (GameAlreadyFinishedException e) {
            return new TryWordResponseDto(2);
        } catch (GameNotFoundException e) {
            return new TryWordResponseDto(2);
        }
    }

    @GetMapping("/get-game")
    public GameDto getGameState() throws GameNotFoundException {
        Long userId = 0L;
        Game game = gameService.getGameByUser(userId);
        return new GameDto(game, gameService, wordService);
    }
}
