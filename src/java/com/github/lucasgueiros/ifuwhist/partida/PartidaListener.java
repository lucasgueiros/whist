/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida;

import java.util.List;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Carta;
import com.github.lucasgueiros.ifuwhist.resultados.Resultado;

/**
 *
 * @author lucas
 */
public interface PartidaListener {
    
    public void gameStarted();
    public void endGame(Resultado result); // o jogo acabou!
    public Carta yourTurn();
    public void cardPlayed(Posicao p, Carta c, int size); // size= quantas cartas sobraram na mão dele
    public void yourCartas(List<Carta> cs);
    
}
