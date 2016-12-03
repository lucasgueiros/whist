package com.github.lucasgueiros.whist.jogador;

import java.util.List;
import java.util.Map;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.eventos.EventoPartida;
import com.github.lucasgueiros.whist.partida.eventos.ListenerPartida;
import com.github.lucasgueiros.whist.partida.excecoes.NaoEstaNaVezException;
import com.github.lucasgueiros.whist.partida.vaza.Carta;
import com.github.lucasgueiros.whist.partida.vaza.Naipe;
import com.github.lucasgueiros.whist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.whist.partida.excecoes.CartaNaoEstaNaMaoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class JogadorFalso implements Jogador, ListenerPartida/* implements RunningMatchListener *//* implements RunningMatchListener *//* implements RunningMatchListener *//* implements RunningMatchListener */ {

    private static int fakeNumber = 0;
    private final String login;
    private final Posicao minhaPosicao;

    public JogadorFalso(Posicao minhaPosicao) {
        this.login = "robot" + fakeNumber++;
        this.minhaPosicao = minhaPosicao;
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
     * Pede para o fake player fazer uma jogada Caso this seja o primeiro
     * jogador da trick atual, trick.get(first) é null
     *
     * @param trick Mapeia as cartas jogadas na trick atual
     * @param first Primeiro jogador da trick atual
     * @param trumph O naipe de trunfo do jogo
     * @param hand A mão do FakePlayer
     * @return
     */
    public Carta yourTurn(Map<Posicao, Carta> trick, Posicao first, Naipe trumph, List<Carta> hand) {
        if (trick.get(first) == null) { // eu sou o primeiro jogador
            return hand.get(0);
        } else { // já jogaram
            Naipe current = trick.get(first).getNaipe();
            if (hasNaipe(hand, current)) {
                return maxFromNaipe(hand, current);
            } else if (hasNaipe(hand, trumph)) {
                return maxFromNaipe(hand, trumph);
            } else {
                return hand.get(0);
            }
        }
    }

    private boolean hasNaipe(List<Carta> hand, Naipe current) {
        for (Carta c : hand) {
            if (c.getNaipe().equals(current)) {
                return true;
            }
        }
        return false;
    }

    private Carta maxFromNaipe(List<Carta> hand, Naipe current) {
        Carta max = null;
        for (Carta c : hand) {
            if (c.getNaipe().equals(current) && (max == null || max.getSimbolo().compareTo(c.getSimbolo()) == -1)) {
                max = c;
            }
        }
        return max;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public boolean isUsuario() {
        return false;
    }

    @Override
    public void partidaAcabou(EventoPartida informacoesAdicionais) {
        // nada
    }

    @Override
    public synchronized void alguemJogou(EventoPartida evento) {
        if (evento.getVez() == minhaPosicao) {
            try {
                wait(1000);
                Map<Posicao, Carta> trick = evento.getPartida().getVaza();
                Posicao inicial = evento.getPartida().getPrimeiroDaVaza();
                Naipe trunfo = evento.getPartida().getNaipeDeTrunfo();
                List<Carta> hand = evento.getPartida().getMao(minhaPosicao);
                Carta carta = yourTurn(trick, inicial, trunfo, hand);
                evento.getPartida().jogar(minhaPosicao, carta);
            } catch (NaoEstaNaVezException ex) {
                Logger.getLogger(JogadorFalso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CartaNaoEstaNaMaoException ex) {
                Logger.getLogger(JogadorFalso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CartaInvalidaException ex) {
                Logger.getLogger(JogadorFalso.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(JogadorFalso.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
}
