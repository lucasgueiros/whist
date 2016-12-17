/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.vaza;

/**
 *
 * @author lucas
 */
public enum Naipe {
    SPADES(0), HEARTS(13), DIAMONDS(26), CLUBS(39);
    
    @Override
    public String toString() {
        switch(this) {
            case SPADES: return "S";
            case HEARTS: return "H";
            case DIAMONDS: return "D";
            case CLUBS: return "C";
        }
        return null;
    }
    
    public String toStringExt() {
        switch(this) {
            case CLUBS:return "clubs";
            case DIAMONDS: return "diamonds";
            case HEARTS: return "hearts";
            case SPADES: return "spades";
        }
        return "";
    }
    
    Naipe(int numerico) {
        this.numerico = numerico;
    }
    
    private int numerico;
    
    public int getNumerico() {
        return numerico;
    }
    
}
