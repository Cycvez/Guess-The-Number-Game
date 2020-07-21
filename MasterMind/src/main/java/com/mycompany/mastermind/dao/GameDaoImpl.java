/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.dao;

import com.mycompany.mastermind.dto.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author carlo
 */
@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Game addGame(Game game) {
        jdbc.update("INSERT INTO Game(GameStatus, Answer) VALUES (?, ? )",
                game.isGameStatus(),
                game.getAnswer());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        return game;
    }

    @Override
    public Game getGameById(int gameId) {
        Game game = jdbc.queryForObject("SELECT * FROM Game WHERE GameId = ?", new GameMapper(), gameId);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> allGames=jdbc.query("SELECT * FROM Game", new GameMapper());
        return allGames;
    }

    @Override
    public void updateGame(Game game) {
        jdbc.update("UPDATE Game SET GameStatus = ? WHERE GameId = ?",
                game.isGameStatus(), game.getGameId());
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game game = new Game();
            game.setGameId(rs.getInt("GameId"));
            game.setGameStatus(rs.getBoolean("GameStatus"));
            game.setAnswer(rs.getString("Answer"));
            return game;
        }

    }

}
