/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.sala;

import com.github.lucasgueiros.whist.equipe.Equipe;
import com.github.lucasgueiros.whist.equipe.TipoDeEquipe;
import com.github.lucasgueiros.whist.mesa.Mesa;

/**
 *
 * Essa é uma sala que só tem uma mesa!
 * Ela aceita duas equipes: 
 * 
 * @author lucas
 */
public class SalaUmaMesa implements Sala {

    private String nome;
    private Mesa mesa;
    private Equipe equipeNorte,equipeLeste,equipeSul,equipeOeste;

    
    
    public void adicionarEquipeIndividual(Equipe equipe) {
        if(equipe.getTipoDeEquipe()!=TipoDeEquipe.INDIVIDUAL) return;
        if(equipeNorte==null ){
            equipeNorte = equipe;
        } else if(equipeLeste==null){
            equipeLeste = equipe;
        } else if(equipeSul==null){
            equipeSul = equipe;
        } else if(equipeOeste==null){
            equipeOeste = equipe;
        }
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Equipe getEquipeNorte() {
        return equipeNorte;
    }

    public Equipe getEquipeLeste() {
        return equipeLeste;
    }

    public Equipe getEquipeSul() {
        return equipeSul;
    }

    public Equipe getEquipeOeste() {
        return equipeOeste;
    }

    @Override
    public final TipoDeEquipe getTipoDeEquipe() {
        return TipoDeEquipe.INDIVIDUAL;
    }

    @Override
    public void adicionarEquipe(Equipe equipe) {
        if(equipe.getTipoDeEquipe() == TipoDeEquipe.INDIVIDUAL){
            this.adicionarEquipeIndividual(equipe);
        }
    }
    
}
