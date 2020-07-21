/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.dao;

import com.mycompany.mastermind.dto.Game;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author carlo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GameDaoImplTest {

    @Autowired
    GameDao gameDao;

    @Autowired
    JdbcTemplate jdbc;

    public GameDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        jdbc.update("DELETE GameRound.* FROM GameRound");
        jdbc.update("DELETE Game.* FROM Game");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class GameDaoImpl.
     */
    @Test
    public void testAddAndGetGameById() {
        Game game = new Game();
        game.setGameStatus(false);
        game.setAnswer("1234");

        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);

    }

    /**
     * Test of getAllGames method, of class GameDaoImpl.
     */
    @Test
    public void testGetAllGames() {
        Game game = new Game();
        game.setGameStatus(false);
        game.setAnswer("1234");

        game = gameDao.addGame(game);

        Game gameTwo = new Game();
        gameTwo.setGameStatus(false);
        gameTwo.setAnswer("1234");

        gameTwo = gameDao.addGame(gameTwo);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size());
        assertTrue(games.contains(game));
        assertTrue(games.contains(gameTwo));
    }

    /**
     * Test of updateGame method, of class GameDaoImpl.
     */
    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setGameStatus(false);
        game.setAnswer("1234");

        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);
        
        game.setGameStatus(true);
        gameDao.updateGame(game);
        
        assertNotEquals(game, fromDao);
        
        fromDao=gameDao.getGameById(game.getGameId());
        
        assertEquals(game, fromDao);
    }

}
