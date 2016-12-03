package com.github.lucasgueiros.whist.resultados;

import com.github.lucasgueiros.whist.usuario.Usuario;
import com.github.lucasgueiros.whist.util.repositorio.Filtro;

public class FiltroContemJogador implements Filtro<Resultado>{

	private final Usuario jogador;

	public FiltroContemJogador(Usuario jogador) {
		this.jogador = jogador;
	}
	
	@Override
	public boolean filtrar(Resultado resultado) {
		return resultado != null && resultado.containsJogador(jogador);
	}

	
	
}
