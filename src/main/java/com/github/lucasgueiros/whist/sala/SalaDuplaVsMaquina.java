/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.sala;

import com.github.lucasgueiros.whist.equipe.Equipe;
import com.github.lucasgueiros.whist.equipe.TipoDeEquipe;
import com.github.lucasgueiros.whist.jogador.Jogador;
import com.github.lucasgueiros.whist.mesa.Mesa;
import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.JogadorFalso;
import com.github.lucasgueiros.whist.partida.eventos.EventoPartida;

/**
 *
 * @author lucas
 */
public class SalaDuplaVsMaquina implements Sala {

    private String nome;
    
    private Equipe dupla;
    private Jogador falsoE;
    private Jogador falsoW;
    private Mesa mesa;

    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    @Override public String getNome() {
        return nome;
    }

    /**
     * Set the value of nome
     *
     * @param nome new value of nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    @Override
    public Mesa getMesa(Jogador jogador) {
        return dupla != null && dupla.isMembro(jogador) ? mesa : null;
    }

    @Override
    public TipoDeEquipe getTipoDeEquipe() {
        return TipoDeEquipe.DUPLA;
    }

    @Override
    public void adicionarEquipe(Equipe equipe) {
        if(equipe != null && this.dupla == null &&
                equipe.getTipoDeEquipe()==TipoDeEquipe.DUPLA ){
            this.dupla = equipe;
            JogadorFalso jfe = new JogadorFalso(Posicao.EAST);
            JogadorFalso jfw = new JogadorFalso(Posicao.WEST);
            this.falsoE = jfe;
            this.falsoW = jfw;
            this.mesa = new Mesa(this.dupla.getMembro(0), this.dupla.getMembro(1), falsoE, falsoW);
            this.mesa.getPartida().addListener(jfe);
            this.mesa.getPartida().addListener(jfw);
            this.mesa.getPartida().estaPronto(Posicao.EAST);
            this.mesa.getPartida().estaPronto(Posicao.WEST);
        }
    }

    @Override
    public boolean prontoParaJogar() {
        return mesa!=null;
    }

    @Override
    public void partidaAcabou(EventoPartida evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alguemJogou(EventoPartida evento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
