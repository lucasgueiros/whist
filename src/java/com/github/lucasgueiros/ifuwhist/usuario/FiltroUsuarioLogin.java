package com.github.lucasgueiros.ifuwhist.usuario;

import com.github.lucasgueiros.ifuwhist.util.repositorio.Filtro;

class FiltroUsuarioLogin implements Filtro<Usuario>{
	
	private String login = null;

	public FiltroUsuarioLogin(String login) {
		this.login = login;
	}
	
	@Override
	public boolean filtrar(Usuario t) {
		return t!=null && t.getLogin().equals(login);
	}
	
}
