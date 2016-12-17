/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.sala;

import com.github.lucasgueiros.whist.equipe.Equipe;
import com.github.lucasgueiros.whist.equipe.TipoDeEquipe;
import com.github.lucasgueiros.whist.usuario.Usuario;
import com.github.lucasgueiros.whist.util.propriedades.PropriedadesApplicationBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lucas
 */
@ManagedBean
@RequestScoped
public class SalaRequestBean {
    
    private String nomeDaSala;
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeDaSala() {
        return nomeDaSala;
    }

    public void setNomeDaSala(String nomeDaSala) {
        this.nomeDaSala = nomeDaSala;
    }

    public Sala criarSalaUmMesa(){
        SalaUmaMesa sala = new SalaUmaMesa();
        sala.setNome(nomeDaSala);
        
        return sala;
    }
    
    public String preparaCriarSalaUmaMesa(Usuario usuario){
        this.usuario = usuario;
        return PropriedadesApplicationBean.getString("pagina.criarSala.umaMesa");
    }
    
    public Sala criarSalaDuplaVsMaquina(){
        SalaDuplaVsMaquina sala = new SalaDuplaVsMaquina();
        sala.setNome(nomeDaSala);
        return sala;
    }
    
    public Sala criarSalaDuasDuplas(){
        SalaDuasDuplas sala = new SalaDuasDuplas();
        sala.setNome(nomeDaSala);
        return sala;
    }
    
    public Sala criarSalaUmContraMaquina(){
        SalaUmContraMaquinas sala = new SalaUmContraMaquinas();
        sala.setNome(nomeDaSala);
        return sala;
    }
    
}
