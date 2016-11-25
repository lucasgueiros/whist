package com.github.lucasgueiros.ifuwhist.partida;

import java.util.List;
import java.util.Map;

import com.github.lucasgueiros.ifuwhist.jogador.Usuario;
import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Carta;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Naipe;

/**
 *
 * @author lucas
 */
public class JogadorFalso extends Usuario/* implements RunningMatchListener *//* implements RunningMatchListener */{

    private static int fakeNumber = 0;
    
    public JogadorFalso() {
        super("Robot", "robot" + fakeNumber++, "robot");
    }
    
    @Override
    public boolean isFake() {
        return true;
    }
/*
    @Override
    public void update() {
        // nothing to do
    }

    @Override
    public void yourTurn() {
        
    }

    @Override
    public void endGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */

    /**
     *  
     * Pede para o fake player fazer uma jogada
     * Caso this seja o primeiro jogador da trick atual, trick.get(first) é null
     * 
     * @param trick Mapeia as cartas jogadas na trick atual
     * @param first Primeiro jogador da trick atual
     * @param trumph O naipe de trunfo do jogo
     * @param hand A mão do FakePlayer
     * @return 
     */
    public Carta yourTurn(Map<Posicao, Carta> trick, Posicao first, Naipe trumph, List<Carta> hand) {
        if(trick.get(first)==null) { // eu sou o primeiro jogador
            return hand.get((int) Math.random() * hand.size() - 1);
        } else { // já jogaram
            Naipe current  = trick.get(first).getNaipe();
            if(hasNaipe(hand,current)) {
                return maxFromNaipe(hand,current);
            } else if (hasNaipe(hand,trumph)) {
                return maxFromNaipe(hand,trumph);
            } else {
                return hand.get((int) Math.random() * hand.size() - 1);
            }
        }
    }

    private boolean hasNaipe(List<Carta> hand, Naipe current) {
        for(Carta c : hand)  {
            if(c.getNaipe().equals(current))  {
                return true;
            }
        }
        return false;
    }

    private Carta maxFromNaipe(List<Carta> hand, Naipe current) {
        Carta max = null;
        for(Carta c : hand)  {
            if(c.getNaipe().equals(current) && ( max == null  || max.getSimbolo().compareTo(c.getSimbolo()) == -1) )  {
                max = c;
            }
        }
        return max;
    }
}
