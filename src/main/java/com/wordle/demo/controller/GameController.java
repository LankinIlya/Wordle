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

    @CrossOrigin
    @GetMapping("/new_game")
    public String newGame() {
<<<<<<< HEAD
        Long userId = 0L;
=======
        Long userId = 1L;
>>>>>>> 3ca1effc5be888ace247b05dfbf07bd25ab404d7
        gameService.newGame(userId);
        return "ok";
    }

    @CrossOrigin
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
<<<<<<< HEAD
=======
    }

    @GetMapping("/get-game")
    public GameDto getGameState() throws GameNotFoundException {
        Long userId = 0L;
        Game game = gameService.getGameByUser(userId);
        return new GameDto(game, gameService, wordService);
>>>>>>> 3ca1effc5be888ace247b05dfbf07bd25ab404d7
    }

    @CrossOrigin
    @GetMapping("/get_game")
    public GameDto getGameState() throws GameNotFoundException {
        Long userId = 0L;
        Game game = gameService.getGameByUser(userId);
        return new GameDto(game, gameService, wordService);
    }

//    @CrossOrigin
//    @GetMapping("/get_game")
//    public Game getGameState() throws GameNotFoundException {
//        Long userId = 0L;
//        System.out.println("----------getGameState----------");
//        Game game = gameService.getGameByUser(userId);
//        System.out.println("----------getGameState-----game-----");
//        return game;
//    }
}
