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
import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.JogadorFalso;
import com.github.lucasgueiros.whist.usuario.Usuario;

/**
 *
 * @author lucas
 */
public class SalaUmContraMaquinas implements Sala {

    private String nome;
    private Mesa mesa;
    private Equipe equipe;
    
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Mesa getMesa(Jogador jogador) {
        return mesa;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public TipoDeEquipe getTipoDeEquipe() {
        return TipoDeEquipe.INDIVIDUAL;
    }

    @Override
    public void adicionarEquipe(Equipe equipe) {
        if(equipe!=null && equipe.getTipoDeEquipe() == TipoDeEquipe.INDIVIDUAL && this.equipe == null){
            this.equipe = equipe;
            Usuario north = equipe.getMembro(0);
            
            JogadorFalso south = new JogadorFalso(Posicao.SOUTH);
            JogadorFalso east = new JogadorFalso(Posicao.EAST);
            JogadorFalso west = new JogadorFalso(Posicao.WEST);
            
            this.mesa = new Mesa(north, south, east, west);
            
            this.mesa.getPartida().addListener(south);
            this.mesa.getPartida().addListener(east);
            this.mesa.getPartida().addListener(west);
            
            this.mesa.getPartida().estaPronto(Posicao.NORTH);
            this.mesa.getPartida().estaPronto(Posicao.SOUTH);
            this.mesa.getPartida().estaPronto(Posicao.EAST);
            this.mesa.getPartida().estaPronto(Posicao.WEST);
        }
    }

    @Override
    public boolean prontoParaJogar() {
        return this.mesa != null;
    }
    
}
