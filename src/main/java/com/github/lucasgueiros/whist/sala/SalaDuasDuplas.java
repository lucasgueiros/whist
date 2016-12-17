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
public class SalaDuasDuplas implements Sala {

    private String nome;
    private Mesa mesa;
    
    private Equipe equipeNS;
    private Equipe equipeEW;
    
    
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
        return TipoDeEquipe.DUPLA;
    }

    @Override
    public void adicionarEquipe(Equipe equipe) {
        if(equipe != null && equipeEW == null) {
            this.equipeEW = equipe;
            
        } else if(equipe != null && equipeNS == null) {
            this.equipeNS = equipe;
        }
        if(equipeNS != null && equipeEW != null){
            this.mesa = new Mesa(equipeNS.getMembro(0), equipeNS.getMembro(1), equipeEW.getMembro(0), equipeEW.getMembro(1));
        }
    }

    @Override
    public boolean prontoParaJogar() {
        return mesa != null;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
