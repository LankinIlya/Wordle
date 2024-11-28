package com.wordle.demo.controller;

import com.wordle.demo.controller.dto.GameDto;
import com.wordle.demo.controller.dto.TopsDto;
import com.wordle.demo.controller.dto.TryWordDto;
import com.wordle.demo.controller.dto.TryWordResponseDto;
import com.wordle.demo.exception.GameAlreadyFinishedException;
import com.wordle.demo.exception.GameNotFoundException;
import com.wordle.demo.exception.IncorrectGuessException;
import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.jwt.JwtTokenUtil;
import com.wordle.demo.service.GameService;
import com.wordle.demo.service.UserService;
import com.wordle.demo.service.WordService;
import com.wordle.demo.service.model.Game;
import com.wordle.demo.service.model.MyUser;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final WordService wordService;
    private final JwtTokenUtil jwtTokenUtil;
    private static final String origin = "http://localhost:3000";
    private final UserService userService;

    public GameController(GameService gameService,
                          WordService wordService,
                          JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.gameService = gameService;
        this.wordService = wordService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @CrossOrigin//(origins = origin)
    @GetMapping("/new_game")
    public String newGame() {
        Long userId = 0L;
        gameService.newGame(userId);
        return "ok";
    }



    @CrossOrigin//(origins = origin)
    @PostMapping("/try_word")
    public TryWordResponseDto tryWord(
            @CookieValue(value = "jwt", defaultValue = "") String token,
            @RequestBody TryWordDto tryWordDto,
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            return new TryWordResponseDto(
                gameService.tryWord(tryWordDto.gameId(), tryWordDto.word())
            );
        }
        catch (WordNotFoundException | IncorrectGuessException ex) {
            return new TryWordResponseDto(1);
        }
        catch (GameAlreadyFinishedException | GameNotFoundException e) {
            return new TryWordResponseDto(2);
        }
    }

    @CrossOrigin//(origins = origin)
    @GetMapping("/get_game")
    public GameDto getGameState(
            @CookieValue(value = "jwt", defaultValue = "") String token,
            @CookieValue(value = "game_id", defaultValue = "") String gameIdStr,
            HttpServletResponse response)
            throws GameNotFoundException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Long userId = getUserId(token);
//        System.out.println("GameController.getGameState: " +
//                "userId=" + userId + ", gameIdStr=" + gameIdStr);
        if(gameIdStr == null || gameIdStr.isEmpty()) {
//            System.out.println("gameIdStr is empty");
            Game game = gameService.getGameByUser(userId);
            return new GameDto(game, gameService, wordService);
        } else {
//            System.out.println("gameIdStr=" + gameIdStr);
            Long gameId = Long.parseLong(gameIdStr);
            Game game = gameService.getGameById(gameId, userId);
            return new GameDto(game, gameService, wordService);
        }
    }


    @CrossOrigin//(origins = origin)
    @GetMapping("/get_tops")
    public TopsDto getTops(
            @CookieValue(value = "jwt", defaultValue = "") String token,
            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        Long userId = getUserId(token);
        return userService.getTops(userId);
    }




    Long getUserId(String token) {
        return (token.isEmpty()
                ? MyUser.ANONYMOUS_ID
                : jwtTokenUtil.getIdFromToken(token));
    }







}
