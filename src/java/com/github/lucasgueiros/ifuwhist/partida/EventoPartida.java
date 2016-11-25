/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida;

/**
 *
 * @author ogi
 */
public class EventoPartida {
    private Partida partida;

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public EventoPartida(Partida partida) {
        this.partida = partida;
    }
    
    
}
