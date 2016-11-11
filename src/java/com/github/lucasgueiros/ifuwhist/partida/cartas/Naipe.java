/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida.cartas;

/**
 *
 * @author lucas
 */
public enum Naipe {
    SPADES, HEARTS, DIAMONDS, CLUBS;
    
    public String toString() {
        switch(this) {
            case SPADES: return "S";
            case HEARTS: return "H";
            case DIAMONDS: return "D";
            case CLUBS: return "C";
        }
        return null;
    }
    
}
