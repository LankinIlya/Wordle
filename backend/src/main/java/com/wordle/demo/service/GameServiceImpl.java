package com.wordle.demo.service;

import com.wordle.demo.exception.GameAlreadyFinishedException;
import com.wordle.demo.exception.GameNotFoundException;
import com.wordle.demo.exception.IncorrectGuessException;
import com.wordle.demo.exception.WordNotFoundException;
import com.wordle.demo.repository.GameRepository;
import com.wordle.demo.repository.TryWordRepository;
import com.wordle.demo.repository.UserRepository;
import com.wordle.demo.repository.WordRepository;
import com.wordle.demo.repository.entity.GameEntity;
import com.wordle.demo.repository.entity.TryWordEntity;
import com.wordle.demo.repository.entity.UserEntity;
import com.wordle.demo.repository.entity.WordEntity;
import com.wordle.demo.service.model.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService{
    private static final int NUMBER_OF_LETTERS = 5;
    private static final int NUMBER_OF_WORDS = 6;
    private final WordRepository wordRepository;
    private final GameRepository gameRepository;
    private final TryWordRepository tryWordRepository;
    private final WordService wordService;
    private final UserRepository userRepository;


    public GameServiceImpl(WordRepository wordRepository,
                           GameRepository gameRepository,
                           TryWordRepository tryWordRepository,
                           WordService wordService,
                           UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.gameRepository = gameRepository;
        this.tryWordRepository = tryWordRepository;
        this.wordService = wordService;
        this.userRepository = userRepository;
    }

    public Game newGame(Long userId) {
        Word word = chooseRandomWord();
        GameEntity gameEntity = gameRepository.save(
                new GameEntity(userId, true, false, word.id()));
        if (userId != MyUser.ANONYMOUS_ID){
            userRepository.incGames(userId );
        }
        return new Game(gameEntity, wordService);
    }

    public TryWordResult tryWord(Long gameId, String guess)
            throws WordNotFoundException,
            IncorrectGuessException,
            GameNotFoundException,
            GameAlreadyFinishedException {

        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if(gameEntity.isEmpty())
            throw new GameNotFoundException();

        Game game = new Game(gameEntity.get(), wordService);
        if(!game.getActive())
            throw new GameAlreadyFinishedException();

        if(guess.length() != game.getWord().text().length())
            throw new IncorrectGuessException();

        Long numberOfTries = tryWordRepository.getCountByGameId(game.getId());

        Optional<WordEntity> wordEntity = wordRepository.findByText(guess);
        if(wordEntity.isEmpty())
            throw new IncorrectGuessException();

        System.out.println("TryWord: " + guess +
                "  gameWord = " + game.getWord().text());

        List<Integer> result = getResult(game.getWord().text(), guess);

        boolean win = true;

        for(int i = 0; i < result.size(); i++) {
            if(result.get(i) < 2) {
                win = false;
                break;
            }
        }

        tryWordRepository.save(new TryWordEntity(
                game.getId(),
                wordEntity.get().getId(),
                numberOfTries));

        numberOfTries++;
        if(numberOfTries == NUMBER_OF_WORDS || win) {
            gameRepository.finishGame(game.getId(), win);
            if (game.getUserId() != MyUser.ANONYMOUS_ID && win) {
                userRepository.incWins(game.getUserId() );
            }
            return new TryWordResult(result, false, win,
                    game.getWord().text());
        }

        return new TryWordResult(result);
    }

    @Override
    public Game getGameByUser(Long userId) {
        if(userId == MyUser.ANONYMOUS_ID)
            return newGame(userId);

        Optional<GameEntity> gameEntity = gameRepository.findByUserId(userId);
        if(gameEntity.isEmpty()) {
            return newGame(userId);
        } else {
            return new Game(gameEntity.get(), wordService);
        }
    }

    @Override
    public Game getGameById(Long gameId, Long userId) {
        System.out.println("gameId = " + gameId + " userId = " + userId);
        Optional<GameEntity> gameEntity = gameRepository.findById(gameId);
        if( gameEntity.isEmpty()
            || gameEntity.get().getUserId() != userId
            || !gameEntity.get().getActive() ) {
            return getGameByUser(userId);
        } else {
            return new Game(gameEntity.get(), wordService);
        }
    }

    private Word chooseRandomWord() {
        long amount = wordRepository.count();
        int index = new Random().nextInt((int)amount);
        Pageable pageable = PageRequest.of(index, 1);
        List<WordEntity> result = wordRepository.findAll(pageable)
                                                .getContent();

        try {
            WordEntity wordEntity = result.get(0);
            System.out.println("chooseRandomWord id = "
                    + wordEntity.getId()
                    + "  text = "
                    + wordEntity.getText() );
            return new Word(wordEntity.getId(), wordEntity.getText());
        }
        catch (Exception ex) {
            throw ex;
        }
    }

    public List<Integer> getResult(String word, String guess) {
        List<Integer> result = new ArrayList<>(word.length());
        for(int i = 0; i < word.length(); i++) {
            result.add(0);
            if(word.charAt(i) == guess.charAt(i)) {
                result.set(i, 2);
            } else {
                for(int j = 0; j < word.length(); j++) {
                    if(word.charAt(j) == guess.charAt(i)) {
                        result.set(i, 1);
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<TryWord> getTryWords(Long gameId) {
        List<TryWordEntity> entities = tryWordRepository.findByGameId(gameId);
        List<TryWord> result = new ArrayList<>(entities.size());
        for(TryWordEntity entity : entities) {
            try {
                result.add(new TryWord(entity, wordService));
            } catch (WordNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
