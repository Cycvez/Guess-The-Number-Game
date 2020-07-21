/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.dao;

import com.mycompany.mastermind.dto.Round;
import java.util.List;

/**
 *
 * @author carlo
 */

public interface RoundDao {
    
    List<Round> getAllRounds(int gameId);
    Round getRoundById(int roundId);
    Round addRound(Round round);
}
