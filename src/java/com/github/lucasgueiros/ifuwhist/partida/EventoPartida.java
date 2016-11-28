/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;

/**
 *
 * @author ogi
 */
public class EventoPartida {
    private PartidaInterface partida;
    private Posicao vez;

    public PartidaInterface getPartida() {
        return partida;
    }

    public void setPartida(PartidaInterface partida) {
        this.partida = partida;
    }

    public EventoPartida(PartidaInterface partida) {
        this.partida = partida;
    }

    public void setVez(Posicao turn) {
        this.vez=turn;
    }

    public Posicao getVez() {
        return vez;
    }
    
}
