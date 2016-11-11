package com.github.lucasgueiros.ifuwhist.resultados;

import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import com.github.lucasgueiros.ifuwhist.util.repositorio.Filtro;

public class FiltroContemJogador implements Filtro<Resultado>{

	private final Jogador jogador;

	public FiltroContemJogador(Jogador jogador) {
		this.jogador = jogador;
	}
	
	@Override
	public boolean filtrar(Resultado resultado) {
		return resultado != null && resultado.containsJogador(jogador);
	}

	
	
}
