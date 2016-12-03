/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.partida.vaza;


/**
 *
 * @author lucas
 */
public enum Simbolo {
    A(13), K(12), Q(11), J(10), N10(9), N9(8), N8(7), N7(6), N6(5), N5(4), N4(3), N3(2), N2(1);
    private int n;
    private Simbolo (int n) {
        this.n = n;
    }
    public int toInt() {
        return n;
    }

    @Override
    public String toString() {
        switch(n) {
            case 13: return "A";
            case 12: return "K";
            case 11: return "Q";
            case 10: return "J";
            default:
                return "" + (n + 1);
        }
    }
    
    public String toStringExt(){
        switch(this) {
            case A: return "ace"; 
            case K: return "king"; 
            case Q: return "queen"; 
            case J: return "jack";
            default: return toString();
        }
    }
    
}
