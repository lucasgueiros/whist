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
    public void partidaAcabou(EventoPartida evento);

    public void alguemJogou(EventoPartida evento);
}
