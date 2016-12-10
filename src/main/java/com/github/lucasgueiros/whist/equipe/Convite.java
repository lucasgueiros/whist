/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.equipe;

import com.github.lucasgueiros.whist.sala.Sala;
import com.github.lucasgueiros.whist.usuario.Usuario;

/**
 *
 * @author lucas
 */
public class Convite {
    
    private Usuario alvo;
    private Usuario origem;
    private String mensagem;
    private Sala sala;
    private Equipe equipe;
    private boolean aceito;
    private String nome;

    public Convite(Usuario alvo, Usuario origem, String mensagem, Sala sala) {
        this.alvo = alvo;
        this.origem = origem;
        this.mensagem = mensagem;
        this.sala = sala;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    public boolean isAceito() {
        return aceito;
    }

    public void setAceito(boolean aceito) {
        this.aceito = aceito;
    }
    
    public void aceitar(){
        this.aceito = true;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Equipe getEquipe() {
        if(equipe==null && aceito){
            this.equipe = new Equipe(TipoDeEquipe.DUPLA);
            equipe.setMembros(alvo,origem);
        }
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
    
    public Usuario getAlvo() {
        return alvo;
    }

    public void setAlvo(Usuario alvo) {
        this.alvo = alvo;
    }

    public Usuario getOrigem() {
        return origem;
    }

    public void setOrigem(Usuario origem) {
        this.origem = origem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
