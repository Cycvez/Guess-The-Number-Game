/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.dao;

import com.mycompany.mastermind.dto.Game;
import java.util.List;

/**
 *
 * @author carlo
 */

public interface GameDao {
    Game addGame(Game game);
    Game getGameById(int gameId);
    List<Game> getAllGames();
    void updateGame(Game game);
}
