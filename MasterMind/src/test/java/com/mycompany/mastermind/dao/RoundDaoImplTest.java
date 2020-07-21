/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.dao;

import com.mycompany.mastermind.dto.Game;
import com.mycompany.mastermind.dto.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
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
public class RoundDaoImplTest {

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;

    @Autowired
    JdbcTemplate jdbc;

    public RoundDaoImplTest() {
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
     * Test of getRoundById method, of class RoundDaoImpl.
     */
    @Test
    public void testAddAndGetRoundById() {
        Game game = new Game();
        game.setGameStatus(false);
        game.setAnswer("1234");

        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("4567");
        round.setExact("0");
        round.setPartial("1");
        round.setTimestamp(LocalDateTime.now().withNano(0));

        round = roundDao.addRound(round);
        Round fromDao = roundDao.getRoundById(round.getRoundId());

        assertEquals(fromDao, round);

    }

    /**
     * Test of getAllRounds method, of class RoundDaoImpl.
     */
    @Test
    public void testGetAllRounds() {
        Game game = new Game();
        game.setGameStatus(false);
        game.setAnswer("1234");

        game = gameDao.addGame(game);

        Round round = new Round();
        round.setGameId(game.getGameId());
        round.setGuess("4567");
        round.setExact("0");
        round.setPartial("1");
        round.setTimestamp(LocalDateTime.now().withNano(0));

        round = roundDao.addRound(round);

        Round roundTwo = new Round();
        roundTwo.setGameId(game.getGameId());
        roundTwo.setGuess("4130");
        roundTwo.setExact("1");
        roundTwo.setPartial("2");
        roundTwo.setTimestamp(LocalDateTime.now().withNano(0));
        
        roundTwo= roundDao.addRound(roundTwo);
        
        List<Round> rounds= roundDao.getAllRounds(game.getGameId());

        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round));
        assertTrue(rounds.contains(roundTwo));

    }

}
