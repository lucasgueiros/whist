/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida.eventos;

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
}
