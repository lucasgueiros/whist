/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.vaza;

import java.util.Arrays;
import java.util.Comparator;
import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author lucas
 */
@XmlJavaTypeAdapter (Carta.StringAdpater.class)
public final class Carta implements Serializable, Comparable<Carta> {
    
    private static final long serialVersionUID = 2L;

    /*
     * This card defines the trumph suit for each deal
    **/
    private final Naipe naipe; // o naipe
    private final Simbolo simbolo; // simbolo, A, K, Q, J, 10 ... 2

    private static final Carta[] cartas = new Carta[52];

    static {
        int i = 0;
        for (Naipe naipe : Naipe.values()) {
            for (Simbolo simbolo : Simbolo.values()) {
                cartas[i++] = new Carta(naipe, simbolo);
            }
        }
        Arrays.sort(cartas, new Comparator<Carta>() {
            @Override
            public int compare(Carta c1, Carta c2) {
                return c1.hashCode() - c2.hashCode();
            }
        });
    }

    public static Carta getCarta(Naipe naipe, Simbolo simbolo) {
        return cartas[new Carta(naipe, simbolo).hashCode() - 1];
    }

    private Carta(Naipe suit, Simbolo symbol) {
        this.naipe = suit;
        this.simbolo = symbol;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    @Override
    public String toString() {
        return naipe.toString() + simbolo.toString();
    }

    @Override
    public int hashCode() {
        return naipe.getNumerico() + simbolo.toInt();
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
        if (this.naipe != other.naipe) {
            return false;
        }
        if (this.simbolo != other.simbolo) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Carta o) {
        if (this.getNaipe().compareTo(o.getNaipe()) > 0) {
            return +1;
        } else if (this.getNaipe().compareTo(o.getNaipe()) < 0) {
            return -1;
        } else if (this.getSimbolo().compareTo(o.getSimbolo()) < 0) {
            return -1;
        } else if (this.getSimbolo().compareTo(o.getSimbolo()) > 0) {
            return +1;
        } else {
            return 0;
        }
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
                    simbolo = Simbolo.K;
                    break;
                case "Q":
                    simbolo = Simbolo.Q;
                    break;
                case "J":
                    simbolo = Simbolo.J;
                    break;
                case "10":
                    simbolo = Simbolo.N10;
                    break;
                case "9":
                    simbolo = Simbolo.N9;
                    break;
                case "8":
                    simbolo = Simbolo.N8;
                    break;
                case "7":
                    simbolo = Simbolo.N7;
                    break;
                case "6":
                    simbolo = Simbolo.N6;
                    break;
                case "5":
                    simbolo = Simbolo.N5;
                    break;
                case "4":
                    simbolo = Simbolo.N4;
                    break;
                case "3":
                    simbolo = Simbolo.N3;
                    break;
                case "2":
                    simbolo = Simbolo.N2;
                    break;
                default:
                    simbolo = Simbolo.A;
            }
            return Carta.getCarta(naipe, simbolo);
        }
    }
    
}
