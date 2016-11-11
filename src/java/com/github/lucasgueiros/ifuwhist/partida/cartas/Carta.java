/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida.cartas;

import java.util.Objects;

/**
 *
 * @author lucas
 */

public class Carta {
    /*
     * This card defines the trumph suit for each deal
    **/
    private Naipe suit; // o naipe
    private Simbolo symbol; // simbolo, A, K, Q, J, 10 ... 2
 
    public Carta(Naipe suit, Simbolo symbol) {
        this.suit = suit;
        this.symbol = symbol;
    }
    
    public Carta(Simbolo symbol, Naipe suit) {
        this.suit = suit;
        this.symbol = symbol;
    }
    
    public Naipe getNaipe() {
        return suit;
    }
    
    public Simbolo getSimbolo() {
        return symbol;
    }
    
    @Override
    public String toString() {
        return suit.toString() + symbol.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.suit);
        hash = 59 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.symbol != other.symbol) {
            return false;
        }
        return true;
    }
    
    
    
}
