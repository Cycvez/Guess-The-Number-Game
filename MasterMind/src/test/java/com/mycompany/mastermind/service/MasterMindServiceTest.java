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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author carlo
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MasterMindServiceTest {

    @Autowired
    MasterMindService service;

    @Autowired
    GameDao gameDao;

    @Autowired
    RoundDao roundDao;

    public MasterMindServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of beginGame method, of class MasterMindService.
     */
    @Test
    public void testBeginGame() {
    }

    /**
     * Test of createAnswer method, of class MasterMindService.
     */
    @Test
    public void testCreateAnswer() {
        String answerOne = service.createAnswer();
        assertTrue(answerOne.length() == 4);

        String[] guess = answerOne.split("");
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (guess[i].equals(guess[j])) {
                    fail();
                }
            }
        }
    }

    /**
     * Test of checkForExactMatches method, of class MasterMindService.
     */
    @Test
    public void testCheckForExactMatches() {
        String testAnswer = "1234";

        Round testRoundGuess = new Round();
        testRoundGuess.setGameId(1);
        testRoundGuess.setGuess("1789");

        String exact = service.checkForExactMatches(testRoundGuess, testAnswer);

        assertTrue(exact.equals("1"));

    }

    /**
     * Test of checkForPartialMatches method, of class MasterMindService.
     */
    @Test
    public void testCheckForPartialMatches() {
        String testAnswer = "1234";

        Round testRoundGuess = new Round();
        testRoundGuess.setGameId(1);
        testRoundGuess.setGuess("4789");

        String partial = service.checkForPartialMatches(testRoundGuess, testAnswer);

        assertTrue(partial.equals("1"));
    }

}
