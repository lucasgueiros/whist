package com.github.lucasgueiros.ifuwhist.jogador;

import com.github.lucasgueiros.ifuwhist.util.repositorio.Filtro;

class FiltroJogadorLogin implements Filtro<Jogador>{
	
	private String login = null;

	public FiltroJogadorLogin(String login) {
		this.login = login;
	}
	
	@Override
	public boolean filtrar(Jogador t) {
		return t!=null && t.getLogin().equals(login);
	}
	
}
