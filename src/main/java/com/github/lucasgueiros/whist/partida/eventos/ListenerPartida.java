/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.eventos;

/**
 *
 * @author ogi
 */
public interface ListenerPartida {
    /**
     * Será chamado quando uma partida tiver acabado
     * @param evento 
     */
    public void partidaAcabou(EventoPartida evento);
    /**
     * É chamado após cada jogada, seja qual for o jogador
     * @param evento 
     */
    public void alguemJogou(EventoPartida evento);
    /**
     * É chamado cada vez que uma vaza acaba, i.e., a cada 4 jogadas.
     * @param evento 
     */
    public void vazaAcabou(EventoPartida evento);
}
