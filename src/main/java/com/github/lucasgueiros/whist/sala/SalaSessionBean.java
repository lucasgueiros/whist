/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.sala;

import com.github.lucasgueiros.whist.equipe.Equipe;
import com.github.lucasgueiros.whist.mesa.Mesa;
import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.usuario.Usuario;
import com.github.lucasgueiros.whist.util.propriedades.PropriedadesApplicationBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class SalaSessionBean {
    
    private Sala sala;
    
    private Equipe equipe;

    private Mesa mesa;
    
        private Usuario usuario;

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


    /**
     * Get the value of mesa
     *
     * @return the value of mesa
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Set the value of mesa
     *
     * @param mesa new value of mesa
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    
    /**
     * Get the value of equipe
     *
     * @return the value of equipe
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * Set the value of equipe
     *
     * @param equipe new value of equipe
     */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }


    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
    
    public String entrar(Sala sala) {
        this.sala = sala;
        return PropriedadesApplicationBean.getString("pagina.sala");
    }
    
    public String adicionarEquipe(){
        
        this.sala.adicionarEquipe(equipe);
        
        // isso ainda não está bom para quadras!
        //this.mesa = sala.getMesa(equipe.getMembro(0));
        
        return PropriedadesApplicationBean.getString("pagina.sala");
    }
    
    public Mesa getUmaMesa(){
        return this.sala.getMesa(usuario);
    }
    
    public Posicao getPosicao(){
        return this.mesa.getPosicao(usuario);
    }
    
}
