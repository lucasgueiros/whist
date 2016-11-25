/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.resultados;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.EventoPartida;
import com.github.lucasgueiros.ifuwhist.partida.ListenerPartida;
import com.github.lucasgueiros.ifuwhist.partida.Partida;
import com.github.lucasgueiros.ifuwhist.util.repositorio.Repositorio;
import com.github.lucasgueiros.ifuwhist.util.repositorio.RepositorioJPA;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ogi
 */
@ManagedBean
@ApplicationScoped
public class ResultadoApplicationScoped implements ListenerPartida {

    private Repositorio<Resultado> repositorioResultado;

    public ResultadoApplicationScoped() {
        repositorioResultado = new RepositorioJPA<>(Resultado.class);
    }
    
    @Override
    public void partidaAcabou(EventoPartida informacoesAdicionais) {
        if(informacoesAdicionais!=null && 
                informacoesAdicionais.getPartida() !=null &&
                informacoesAdicionais.getPartida().getMesa() !=null){
            Resultado resultado = new Resultado(informacoesAdicionais.getPartida());
            this.repositorioResultado.adicionar(resultado);
        }
    }
    
    
    
}
