/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.equipe;

import com.github.lucasgueiros.whist.usuario.Usuario;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lucas
 */
@ManagedBean
@ApplicationScoped
public class EquipeApplicationBean {
    
    private static List<Convite> convites;
    
    public void adicionarConvite(Convite convite) {
        convites.add(convite);
    }
    
    public List<Convite> recuperarConvite(Usuario usuario){
        List<Convite> paramim = new LinkedList<>();
        for(Convite convite : convites){
            if(convite.getAlvo().equals(usuario)){
                paramim.add(convite);
            }
        }
        return paramim;
    }
    
}
