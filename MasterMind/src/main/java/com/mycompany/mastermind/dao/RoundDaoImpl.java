/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.dao;

import com.mycompany.mastermind.dto.Round;
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
public class RoundDaoImpl implements RoundDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Round> getAllRounds(int gameId) {

        List<Round> rounds = jdbc.query("SELECT * FROM GameRound WHERE GameId = ? "
                + "ORDER BY TimeStamp", new RoundMapper(), gameId);
        return rounds;
    }

    @Override
    public Round getRoundById(int roundId) {
        Round round = jdbc.queryForObject("SELECT * FROM GameRound WHERE RoundId = ?", new RoundMapper(), roundId);
        return round;
    }

    @Override
    public Round addRound(Round round) {

        jdbc.update("INSERT INTO gameRound (GameId, Guess, Exact, Partial, TimeStamp) VALUES (?,?,?,?,?)",
                round.getGameId(),round.getGuess(), round.getExact(),
                round.getPartial(), round.getTimestamp().withNano(0));
        Integer newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);
        return round;
    }

    public class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round result = new Round();
            result.setRoundId(rs.getInt("RoundId"));
            result.setGameId(rs.getInt("GameId"));
            result.setGuess(rs.getString("Guess"));
            result.setExact(rs.getString("Exact"));
            result.setPartial(rs.getString("Partial"));
            result.setTimestamp(rs.getTimestamp("TimeStamp").toLocalDateTime());
            return result;
        }

    }
}
