/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.sala;

import com.github.lucasgueiros.whist.util.propriedades.PropriedadesApplicationBean;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lucas
 */
@ManagedBean
@ApplicationScoped
public class SalaApplicationBean {
    
    private final Set<Sala> salasDisponiveis;
    
    public SalaApplicationBean(){
        salasDisponiveis = new HashSet<>();
    }
    
    public String adicionarSala(Sala sala){
        salasDisponiveis.add(sala);
        return PropriedadesApplicationBean.getString("pagina.index"); // algum lugar?
    }
    
    public Collection<Sala> getSalasDisponiveis() {
        Collection<Sala> lista = new LinkedList<>();
        for(Sala sala : salasDisponiveis) {
            lista.add(sala);
        }
        return lista;
    }
    
}
