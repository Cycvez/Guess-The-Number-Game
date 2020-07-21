/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mastermind.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author carlo
 */
public class Round {

    private int roundId;
    private int gameId;
    private String guess;
    private String exact;
    private String partial;
    private LocalDateTime timestamp;

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getExact() {
        return exact;
    }

    public void setExact(String exact) {
        this.exact = exact;
    }

    public String getPartial() {
        return partial;
    }

    public void setPartial(String partial) {
        this.partial = partial;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.roundId;
        hash = 79 * hash + this.gameId;
        hash = 79 * hash + Objects.hashCode(this.guess);
        hash = 79 * hash + Objects.hashCode(this.exact);
        hash = 79 * hash + Objects.hashCode(this.partial);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.exact, other.exact)) {
            return false;
        }
        if (!Objects.equals(this.partial, other.partial)) {
            return false;
        }
        return true;
    }

}
