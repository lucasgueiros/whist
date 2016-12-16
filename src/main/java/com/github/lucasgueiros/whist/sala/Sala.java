/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.sala;

import com.github.lucasgueiros.whist.equipe.Equipe;
import com.github.lucasgueiros.whist.equipe.TipoDeEquipe;
import com.github.lucasgueiros.whist.jogador.Jogador;
import com.github.lucasgueiros.whist.mesa.Mesa;

/**
 *
 * @author lucas
 */
public interface Sala {
    
    public void setNome(String nome);
    
    public Mesa getMesa(Jogador jogador);
    
    public String getNome();
    
    public TipoDeEquipe getTipoDeEquipe();
    
    public void adicionarEquipe(Equipe equipe);
    // adicionar métodos depois, conforme for necessário.
    
    // não faz sentido, mas tudo bem kkkkk
    public boolean prontoParaJogar();
}
