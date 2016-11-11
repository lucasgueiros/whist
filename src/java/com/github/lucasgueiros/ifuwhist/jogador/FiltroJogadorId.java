package com.github.lucasgueiros.ifuwhist.jogador;

import com.github.lucasgueiros.ifuwhist.util.repositorio.Filtro;

/**
 * Apesar de essa classe existir, em breve pretendo criar um filtro genérico de ID, caso seja possível.
 * @author lucas
 *
 */
class FiltroJogadorId implements Filtro<Jogador>{

	private final long id;
	
	public FiltroJogadorId(long id) {
		this.id = id;
	}
	
	@Override
	public boolean filtrar(Jogador t) {
		return t!=null && t.getId() == id;
	}

	
	
}
