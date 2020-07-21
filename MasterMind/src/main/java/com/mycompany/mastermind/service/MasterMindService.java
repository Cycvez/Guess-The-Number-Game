/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.service;

import com.mycompany.mastermind.dao.GameDao;
import com.mycompany.mastermind.dao.RoundDao;
import com.mycompany.mastermind.dto.Game;
import com.mycompany.mastermind.dto.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author carlo
 */
@Service
public class MasterMindService {

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;


    public int beginGame() {
        Game newGame = new Game();
        newGame.setGameStatus(false);
        newGame.setAnswer(createAnswer());
        gameDao.addGame(newGame);
        return newGame.getGameId();
    }

    public String createAnswer() {
        Random rng = new Random();
        List<Integer> list = new ArrayList<>();
        String answer = "";

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (int i = 0; i < 4; i++) {
            int number = list.remove(rng.nextInt(list.size()));
            answer += String.valueOf(number);
        }
        return answer;
    }

    public Round userGuess(Round userGuess) throws InvalidDataException {
       String charCheck=userGuess.getGuess().replaceAll("[^\\d.]", "");
       if(charCheck.length()<4||charCheck.contains(".")){
           throw new InvalidDataException("Guess contains non-numeric values");
       }
        String[] guess = userGuess.getGuess().split("");
        for (int i = 0; i < 4; i++) {
            for (int j = i+1; j < 4; j++) {
                if (guess[i].equals(guess[j])) {
                    throw new InvalidDataException("Guess has a repeating digit");
                }
                if (guess.length < 4) {
                    throw new InvalidDataException("Guess does not have enough digits");
                }
                if (guess.length > 4) {
                    throw new InvalidDataException("Guess has too many digits");
                }
            }
        }

        Game currentGame = gameDao.getGameById(userGuess.getGameId());
        if (currentGame.getAnswer().equals(userGuess.getGuess())) {
            currentGame.setGameStatus(true);
            gameDao.updateGame(currentGame);
            userGuess.setExact("4");
            userGuess.setPartial("0");
        } else {
            String exact = checkForExactMatches(userGuess, currentGame.getAnswer());
            userGuess.setExact(exact);
            String partial = checkForPartialMatches(userGuess, currentGame.getAnswer());
            userGuess.setPartial(partial);
        }
        userGuess.setTimestamp(LocalDateTime.now().withNano(0));
        //REVISIT TO SEE IF NEEDED*********************************************
        roundDao.addRound(userGuess);
        Round currentRound = roundDao.getRoundById(userGuess.getRoundId());
        return currentRound;
    }

    public String checkForExactMatches(Round userGuess, String gameAnswer) {
        String[] guess = userGuess.getGuess().split("");
        String[] answer = gameAnswer.split("");
        int exactMatch = 0;
        for (int i = 0; i < 4; i++) {
            if (guess[i].equals(answer[i])) {
                exactMatch++;
            }
        }
        String exact = String.valueOf(exactMatch);
        return exact;
    }

    public String checkForPartialMatches(Round userGuess, String gameAnswer) {
        String[] guess = userGuess.getGuess().split("");
        String[] answer = gameAnswer.split("");
        int partialMatch = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (guess[i].equals(answer[j]) && !guess[i].equals(answer[i])) {
                    partialMatch++;
                }
            }
        }
        String partial = String.valueOf(partialMatch);
        return partial;
    }

    public List<Game> unfinishedGames() {
        List<Game> inProgress = gameDao.getAllGames();
        for (Game game : inProgress) {
            if (game.isGameStatus() == false) {
                game.setAnswer("");
            }
        }
        return inProgress;
    }

    public Game getGameById(int gameId) {
        Game currentGame = gameDao.getGameById(gameId);
        if (currentGame.isGameStatus() == false) {
            currentGame.setAnswer("");
        }
        return currentGame;
    }

    public List<Round> getAllRoundsForGame(int gameId) {
        List<Round> rounds = roundDao.getAllRounds(gameId);
        return rounds;
    }

}
