/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.mesa;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.github.lucasgueiros.ifuwhist.jogador.UsuarioSessionBean;
import com.github.lucasgueiros.ifuwhist.jogador.Usuario;
import java.io.Serializable;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class MesasSessionBean  implements Serializable {
    
    @ManagedProperty (value = "#{jogadorSessionBean}")
    private UsuarioSessionBean auth;
    
    @ManagedProperty (value = "#{mesasApplicationBean}")
    private MesasApplicationBean mesas;
    
    /**
     * O jogador atual
     */
    private Usuario myself;
    /**
     * O parceiro dele.
     */
    private Usuario parceiro;
    /**
     * Oponente direita
     */
    private Usuario direita;
    /**
     * oponente esquerda
     */
    private Usuario esquerda;
    /**
     * Jogador selecionado
     */
    private Usuario selected;
    

    public UsuarioSessionBean getAuth() {
        return auth;
    }

    public void setAuth(UsuarioSessionBean auth) {
        this.auth = auth;
    }

    public MesasApplicationBean getMesas() {
        return mesas;
    }

    public void setMesas(MesasApplicationBean mesas) {
        this.mesas = mesas;
    }

    public Usuario getMyself() {
        return myself;
    }

    public void setMyself(Usuario myself) {
        this.myself = myself;
    }

    public Usuario getParceiro() {
        return parceiro;
    }

    public void setParceiro(Usuario parceiro) {
        this.parceiro = parceiro;
    }

    public Usuario getDireita() {
        return direita;
    }

    public void setDireita(Usuario direita) {
        this.direita = direita;
    }

    public Usuario getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Usuario esquerda) {
        this.esquerda = esquerda;
    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }
    
    public List<Usuario> getDisponiveis() {
        List<Usuario> ps =  this.mesas.getDisponiveis();
        ps.remove(this.myself);
        return ps;
    }
    
    public void selectEsquerda() {
        this.esquerda = this.selected;
        this.selected = null;
    }
    
    public void selectDireita() {
        this.direita = this.selected;
        this.selected = null;
    }
    
    public void selectParceiro() {
        this.parceiro = this.selected;
        this.selected = null;
    }
    
    public Mesa getMesa() {
    	this.myself = auth.getJogador();
        return mesas.createMesa(myself, esquerda, parceiro, direita);
    }
    
    public String playWith() {
        this.myself = auth.getJogador();
        Mesa table = mesas.createMesa(myself, esquerda, parceiro, direita);
        return null;
    }    
    
}
