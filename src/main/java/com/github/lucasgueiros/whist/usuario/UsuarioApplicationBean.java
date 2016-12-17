/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.usuario;

import com.github.lucasgueiros.whist.util.repositorio.FiltroRecuperarTodos;
import com.github.lucasgueiros.whist.util.repositorio.Repositorio;
import com.github.lucasgueiros.whist.util.repositorio.RepositorioJPA;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author lucas
 */
@ManagedBean
@ApplicationScoped
public class UsuarioApplicationBean {
    
    Repositorio<Usuario> repositorio = new RepositorioJPA<>(Usuario.class);
    
    public List<Usuario> getUsuarios(){
        return repositorio.recuperar(new FiltroRecuperarTodos<>());
    }
    
}
