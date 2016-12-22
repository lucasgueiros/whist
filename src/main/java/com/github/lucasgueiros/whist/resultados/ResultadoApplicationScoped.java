/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.resultados;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.eventos.EventoPartida;
import com.github.lucasgueiros.whist.partida.eventos.ListenerPartida;
import com.github.lucasgueiros.whist.partida.Partida;
import com.github.lucasgueiros.whist.util.repositorio.Repositorio;
import com.github.lucasgueiros.whist.util.repositorio.RepositorioJPA;
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
                informacoesAdicionais.getPartida() !=null ){
                //informacoesAdicionais.getPartida().getMesa() !=null){
            Resultado resultado = new Resultado(informacoesAdicionais.getPartida());
            this.repositorioResultado.adicionar(resultado);
        }
    }

    @Override
    public void alguemJogou(EventoPartida evento) {
        //  nada
    }

    @Override
    public void vazaAcabou(EventoPartida evento) {
        // nada
    }
    
    
    
}
