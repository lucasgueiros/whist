/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.equipe;

import com.github.lucasgueiros.whist.usuario.Usuario;
import javax.faces.bean.ManagedBean;
import com.github.lucasgueiros.whist.sala.Sala;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class EquipeRequestBean {
    
    private Usuario usuario;

    private Sala sala;

    public void setSala(Sala sala){
        this.sala = sala;
    }
        
    /**
     * Get the value of usuario
     *
     * @return the value of usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Set the value of usuario
     *
     * @param usuario new value of usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    private Usuario minhaDupla;

    /**
     * Get the value of minhaDupla
     *
     * @return the value of minhaDupla
     */
    public Usuario getMinhaDupla() {
        return minhaDupla;
    }

    /**
     * Set the value of minhaDupla
     *
     * @param minhaDupla new value of minhaDupla
     */
    public void setMinhaDupla(Usuario minhaDupla) {
        this.minhaDupla = minhaDupla;
    }

    
    public Equipe buildIndividual(Usuario usuario){
        Equipe equipe = new Equipe(TipoDeEquipe.INDIVIDUAL);
        equipe.setMembros(usuario);
        return equipe;
    }
    
    public Equipe montarDupla(){
        Equipe equipe = new Equipe(TipoDeEquipe.DUPLA);
        equipe.setMembros(usuario,minhaDupla);
        return equipe;
    }
    
    public Equipe getIndividual(){
        Equipe equipe = new Equipe(TipoDeEquipe.INDIVIDUAL);
        equipe.setMembros(usuario);
        return equipe;
    }
    
    public Equipe getDupla(){
        Equipe equipe = new Equipe(TipoDeEquipe.DUPLA);
        equipe.setMembros(usuario,minhaDupla);
        return equipe;
    }
    
    public void convidar(){
        Convite convite = new Convite(minhaDupla,usuario,"Por favor, aceite.", sala);
        new EquipeApplicationBean().adicionarConvite(convite);
    }
    
}
