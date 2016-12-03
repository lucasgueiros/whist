package com.github.lucasgueiros.whist.usuario;

import com.github.lucasgueiros.whist.util.repositorio.Filtro;

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
