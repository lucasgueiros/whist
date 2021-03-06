/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.eventos;

import com.github.lucasgueiros.whist.mesa.Posicao;

/**
 *
 * @author ogi
 */
public class EventoPartida {
    private PartidaInterface partida;

    public PartidaInterface getPartida() {
        return partida;
    }

    public EventoPartida(PartidaInterface partida) {
        this.partida = partida;
    }

    public Posicao getVez() {
        return partida.getVez();
    }
    
    private Boolean partidaAcabou;

    /**
     * Get the value of partidaAcabou
     *
     * @return the value of partidaAcabou
     */
    public Boolean getPartidaAcabou() {
        return partidaAcabou;
    }

    /**
     * Set the value of partidaAcabou
     *
     * @param partidaAcabou new value of partidaAcabou
     */
    public void setPartidaAcabou(Boolean partidaAcabou) {
        this.partidaAcabou = partidaAcabou;
    }

}
