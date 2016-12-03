package com.github.lucasgueiros.whist.partida.vaza;

import java.util.Comparator;

public class ComparaCartas implements Comparator<Carta> {

	@Override
	public int compare(Carta o1, Carta o2) {
            return o1.compareTo(o2);
	}

}
