/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.mesa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import java.io.Serializable;

/**
 *
 * @author lucas
 */
@ManagedBean (eager=true) // TODO Tirar o name
@ApplicationScoped
public class MesasApplicationBean implements Serializable {
    
    /**
     * Jogadores sem mesa.
     */
    private Set<Jogador> disponiveis = new HashSet<>();
    private Map<Jogador,Mesa> mesas = new HashMap<>();
    
    public List<Jogador> getDisponiveis  () {
        List<Jogador> disps = new LinkedList<>();
        
        for (Jogador player : disponiveis) {
            disps.add(player);
        }
        return disps;
    }
    
    public Mesa createMesa(Jogador north, Jogador east, Jogador south, Jogador west) {
        Mesa t = new Mesa(north,south,east,west);
        mesas.put(west, t);
        mesas.put(north, t);
        mesas.put(south, t);
        mesas.put(east, t);
        return t;
    }

    public void adicionar(Jogador p) {
        disponiveis.add(p);
    }

    public Mesa getMesa(Jogador p) {
        return this.mesas.get(p);
    }
    
}