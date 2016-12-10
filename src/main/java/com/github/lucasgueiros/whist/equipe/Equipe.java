/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.equipe;

import com.github.lucasgueiros.whist.jogador.Jogador;
import com.github.lucasgueiros.whist.usuario.Usuario;

/**
 *
 * @author lucas
 */
public class Equipe {
    
    private final TipoDeEquipe tipoDeEquipe;
    
    private Usuario [] membros;
    
    public Equipe(TipoDeEquipe tipoDeEquipe) {
        this.tipoDeEquipe = tipoDeEquipe;
    }
    
    public void setMembros(Usuario ... usuarios) {
        if(usuarios == null) return;
        if(usuarios.length == tipoDeEquipe.getTamanho()){
            membros = usuarios;
        }
    }
    
    public TipoDeEquipe getTipoDeEquipe() {
        return tipoDeEquipe;
    }
    
    public Usuario getMembro(int  i) {
        if(i >= tipoDeEquipe.getTamanho()) return null;
        return membros[i];
    }
    
    public boolean isMembro(Jogador usuario){
        return usuario != null && ( usuario.equals(membros[0]) || usuario.equals(membros[1]) || usuario.equals(membros[2]) || usuario.equals(membros[3]) );
    }
    
}
