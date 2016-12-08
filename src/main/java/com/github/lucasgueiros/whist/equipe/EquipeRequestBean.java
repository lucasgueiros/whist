/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.equipe;

import com.github.lucasgueiros.whist.usuario.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lucas
 */
@ManagedBean
@RequestScoped
public class EquipeRequestBean {
    
    public Equipe buildIndividual(Usuario usuario){
        Equipe equipe = new Equipe(TipoDeEquipe.INDIVIDUAL);
        equipe.setMembros(usuario);
        return equipe;
    }
    
}
