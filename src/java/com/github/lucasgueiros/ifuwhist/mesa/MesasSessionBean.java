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

import com.github.lucasgueiros.ifuwhist.jogador.JogadorSessionBean;
import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import java.io.Serializable;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class MesasSessionBean  implements Serializable {
    
    @ManagedProperty (value = "#{jogadorSessionBean}")
    private JogadorSessionBean auth;
    
    @ManagedProperty (value = "#{mesasApplicationBean}")
    private MesasApplicationBean mesas;
    
    /**
     * O jogador atual
     */
    private Jogador myself;
    /**
     * O parceiro dele.
     */
    private Jogador parceiro;
    /**
     * Oponente direita
     */
    private Jogador direita;
    /**
     * oponente esquerda
     */
    private Jogador esquerda;
    /**
     * Jogador selecionado
     */
    private Jogador selected;
    

    public JogadorSessionBean getAuth() {
        return auth;
    }

    public void setAuth(JogadorSessionBean auth) {
        this.auth = auth;
    }

    public MesasApplicationBean getMesas() {
        return mesas;
    }

    public void setMesas(MesasApplicationBean mesas) {
        this.mesas = mesas;
    }

    public Jogador getMyself() {
        return myself;
    }

    public void setMyself(Jogador myself) {
        this.myself = myself;
    }

    public Jogador getParceiro() {
        return parceiro;
    }

    public void setParceiro(Jogador parceiro) {
        this.parceiro = parceiro;
    }

    public Jogador getDireita() {
        return direita;
    }

    public void setDireita(Jogador direita) {
        this.direita = direita;
    }

    public Jogador getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Jogador esquerda) {
        this.esquerda = esquerda;
    }

    public Jogador getSelected() {
        return selected;
    }

    public void setSelected(Jogador selected) {
        this.selected = selected;
    }
    
    public List<Jogador> getDisponiveis() {
        List<Jogador> ps =  this.mesas.getDisponiveis();
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
