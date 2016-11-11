package com.github.lucasgueiros.ifuwhist.partida.cartas;

import java.util.Comparator;

public class ComparaCartas implements Comparator<Carta> {

	@Override
	public int compare(Carta o1, Carta o2) {
        if(o1.getNaipe().compareTo(o2.getNaipe()) > 0) {
            return +1;
        } else if (o1.getNaipe().compareTo(o2.getNaipe()) < 0) {
            return -1;
        } else if (o1.getSimbolo().compareTo(o2.getSimbolo()) < 0){
            return -1;
        } else if (o1.getSimbolo().compareTo(o2.getSimbolo()) > 0) {
            return +1;
        } else {
            return 0;
        }
	}

}
