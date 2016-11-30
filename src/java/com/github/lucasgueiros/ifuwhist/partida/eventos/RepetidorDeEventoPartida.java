/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida.eventos;

import com.github.lucasgueiros.ifuwhist.partida.Partida;
import com.github.lucasgueiros.ifuwhist.partida.PartidaInterface;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author lucas
 */
public class RepetidorDeEventoPartida  {
    
    private PartidaInterface partida;
    private Set<ListenerPartida> listeners = new HashSet<>();

    public RepetidorDeEventoPartida(Partida partida) {
        this.partida = partida;
    }
    
    public void adicionar(ListenerPartida e) {
        listeners.add(e);
    }
    
    public void acabou() {
        EventoPartida evento = new EventoPartida(partida);
        for(ListenerPartida listener : this.listeners) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listener.alguemJogou(evento);
                }
            }).start();
        }
    }
    
    public void mudancaDeVez(){
        EventoPartida evento = new EventoPartida(partida);
        for(ListenerPartida listener : this.listeners) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listener.alguemJogou(evento);
                }
            }).start();
        }
    }

}
