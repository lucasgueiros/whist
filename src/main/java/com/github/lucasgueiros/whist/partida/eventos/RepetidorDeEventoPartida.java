/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.eventos;

import java.util.HashSet;
import java.util.Set;

/**
 * Responsável por enviar aos listerners os eventos gerados por uma partida.
 * 
 * @author lucas
 */
public class RepetidorDeEventoPartida  {
    
    private final PartidaInterface partida;
    private final Set<ListenerPartida> listeners = new HashSet<>();

    public RepetidorDeEventoPartida(PartidaInterface partida) {
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
    
    public void mudancaDeVez(boolean partidaAcabou){
        EventoPartida evento = new EventoPartida(partida);
        evento.setPartidaAcabou(partidaAcabou);
        for(ListenerPartida listener : this.listeners) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listener.alguemJogou(evento);
                }
            }).start();
        }
    }
    
    public void vazaAcabou(){
        EventoPartida evento = new EventoPartida(partida);
        for(ListenerPartida listener : this.listeners) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listener.vazaAcabou(evento);
                }
            }).start();
        }
    }

}
