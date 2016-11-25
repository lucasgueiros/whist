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

import com.github.lucasgueiros.ifuwhist.jogador.Usuario;
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
    private Set<Usuario> disponiveis = new HashSet<>();
    private Map<Usuario,Mesa> mesas = new HashMap<>();
    
    public List<Usuario> getDisponiveis  () {
        List<Usuario> disps = new LinkedList<>();
        
        for (Usuario player : disponiveis) {
            disps.add(player);
        }
        return disps;
    }
    
    public Mesa createMesa(Usuario north, Usuario east, Usuario south, Usuario west) {
        Mesa t = new Mesa(north,south,east,west);
        mesas.put(west, t);
        mesas.put(north, t);
        mesas.put(south, t);
        mesas.put(east, t);
        return t;
    }

    public void adicionar(Usuario p) {
        disponiveis.add(p);
    }

    public Mesa getMesa(Usuario p) {
        return this.mesas.get(p);
    }
    
}