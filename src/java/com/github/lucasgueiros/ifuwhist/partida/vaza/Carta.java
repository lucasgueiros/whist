/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida.vaza;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author lucas
 */
public final class Carta implements Comparable<Carta> {

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

}
