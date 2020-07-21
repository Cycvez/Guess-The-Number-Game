/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.controller;

import com.mycompany.mastermind.dto.Game;
import com.mycompany.mastermind.dto.Round;
import com.mycompany.mastermind.service.InvalidDataException;
import com.mycompany.mastermind.service.MasterMindService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author carlo
 */
@RestController
@RequestMapping("/api")
public class MasterMindController {

    @Autowired
    MasterMindService service;

    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        int gameId = service.beginGame();
        return gameId;
    }

    @PostMapping("/guess")
    public Round guess(@RequestBody Round userGuess) throws InvalidDataException{
        Round checkRound = service.userGuess(userGuess);
        return checkRound;
    }

    @GetMapping("/game")
    public List<Game> unfinishedGames() {
        List<Game> unfinishedGames = service.unfinishedGames();
        return unfinishedGames;
    }

    @GetMapping("game/{gameId}")
    public Game getGameById(@PathVariable(value = "gameId") int gameId) {
        Game currentGame = service.getGameById(gameId);
        return currentGame;
    }

    @GetMapping("rounds/{gameId}")
    public List<Round> sortRoundsForGame(@PathVariable(value = "gameId") int gameId) {
        List<Round> rounds = service.getAllRoundsForGame(gameId);
        return rounds;
    }

}
