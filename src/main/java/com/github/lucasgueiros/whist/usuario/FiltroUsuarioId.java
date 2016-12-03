package com.github.lucasgueiros.whist.usuario;

import com.github.lucasgueiros.whist.util.repositorio.Filtro;

/**
 * Apesar de essa classe existir, em breve pretendo criar um filtro genérico de ID, caso seja possível.
 * @author lucas
 *
 */
class FiltroUsuarioId implements Filtro<Usuario>{

	private final long id;
	
	public FiltroUsuarioId(long id) {
		this.id = id;
	}
	
	@Override
	public boolean filtrar(Usuario t) {
		return t!=null && t.getId() == id;
	}

	
	
}
