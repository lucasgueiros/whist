/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida.vaza;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author lucas
 */
@XmlJavaTypeAdapter (Carta.StringAdpater.class)
public class Carta implements Serializable {
    
    private static final long serialVersionUID = 2L;

    /*
     * This card defines the trumph suit for each deal
    **/
    private Naipe suit; // o naipe
    private Simbolo symbol; // simbolo, A, K, Q, J, 10 ... 2
 
    public Carta(){
        
    }
    
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

    public void setSuit(Naipe suit) {
        this.suit = suit;
    }

    public void setSymbol(Simbolo symbol) {
        this.symbol = symbol;
    }

    public static class StringAdpater extends XmlAdapter<String,Carta>{

        @Override
        public String marshal(Carta carta) throws Exception {
            return carta.toString();
        }

        @Override
        public Carta unmarshal(String string) throws Exception {
            char naipeChar = string.charAt(0);
            Naipe naipe;
            switch(naipeChar) {
                case 'S':
                    naipe=Naipe.SPADES;
                    break;
                case 'H':
                    naipe=Naipe.HEARTS;
                    break;
                case 'D':
                    naipe=Naipe.DIAMONDS;
                    break;
                case 'C':
                    naipe=Naipe.CLUBS;
                    break;
                default:
                    naipe=Naipe.SPADES;
                    break;
            }
            String simboloString = string.substring(1);
            Simbolo simbolo;
            switch(simboloString) {
                case "A":
                    simbolo = Simbolo.A;
                    break;
                case "K":
                    simbolo = Simbolo.A;
                    break;
                case "Q":
                    simbolo = Simbolo.A;
                    break;
                case "J":
                    simbolo = Simbolo.A;
                    break;
                case "10":
                    simbolo = Simbolo.A;
                    break;
                case "9":
                    simbolo = Simbolo.A;
                    break;
                case "8":
                    simbolo = Simbolo.A;
                    break;
                case "7":
                    simbolo = Simbolo.A;
                    break;
                case "6":
                    simbolo = Simbolo.A;
                    break;
                case "5":
                    simbolo = Simbolo.A;
                    break;
                case "4":
                    simbolo = Simbolo.A;
                    break;
                case "3":
                    simbolo = Simbolo.A;
                    break;
                case "2":
                    simbolo = Simbolo.A;
                    break;
                default:
                    simbolo = Simbolo.A;
            }
            return new Carta(naipe, simbolo);
        }
    }
    
}
