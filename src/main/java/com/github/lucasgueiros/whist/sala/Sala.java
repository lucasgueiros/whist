/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.sala;

import com.github.lucasgueiros.whist.equipe.Equipe;
import com.github.lucasgueiros.whist.equipe.TipoDeEquipe;

/**
 *
 * @author lucas
 */
public interface Sala {
    
    public String getNome();
    
    public TipoDeEquipe getTipoDeEquipe();
    
    public void adicionarEquipe(Equipe equipe);
    // adicionar métodos depois, conforme for necessário.
}
