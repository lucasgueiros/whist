/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.eventos;

import com.github.lucasgueiros.whist.partida.excecoes.NaoEstaNaVezException;
import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.vaza.Carta;
import com.github.lucasgueiros.whist.vaza.Naipe;
import com.github.lucasgueiros.whist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.whist.partida.CartaNaoEstaNaMaoException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lucas
 */
public interface PartidaInterface {

    /**
     * Retorna o naipe de trunfo da partida.
     * @return 
     */
    public Naipe getNaipeDeTrunfo();

    /**
     * O naipe corrente dessa vaza.
     * @return o objeto Naipe correspondente.
     */
    public Naipe getNaipeCorrente();

    /**
     * Número da vaza atual.
     * Normalmente é igual a getTrickNumber() + 1. Exceto quando o jogo já tiver acabado.
     * 
     * @return a quantidade de vazas feitas.
     * @see getNowTricksForEW()
     */
    public int getNumeroDaVaza();

    /**
     * Quantas vazas a dupla N-S ganhou.
     * 
     * @return a quantidade de vazas feitas.
     * @see getVazasParaEW()
     */
    public int getVazasParaNS(int nvaza);

    /**
     * Quantas vazas a dupla E-W ganhou.
     * 
     * @return a quantidade de vazas feitas.
     * @see getVazasParaNS()
     */
    public int getVazasParaEW(int nvaza);

    /**
     * Retorna o número de cartas na mão de um jogador
     * @param posicao é o jogador
     * @return um int que diz a quantidade de cartas na mão dele.
     */
    public int getNumeroDeCartas(int nvaza,Posicao posicao);

    /**
     * Retorna a posição do jogador que detém a vez.
     * @param posicao
     * @return 
     */
    public boolean estaNaVezDe(Posicao posicao);

    /**
     * Retorna a carta jogada na vaza atual pelo jogador na posição p.
     * @param posicao
     * @param p a posição do jogador
     * @return a carta que ele jogou.s
     */
    public Carta getCartaDaVaza(int nvaza,Posicao posicao);

    /**
     * Recebe uma jogada, analisa-a de acordo com as regras do Whist e gera as consequências.
     * Não se preocupe caso uma exceção seja lançada, apenas avise o usuário e continue a jogar.
     * @param posicao
     * @param carta
     * @throws CartaNaoEstaNaMaoException
     * @throws CartaInvalidaException
     * @throws NaoEstaNaVezException 
     */
    public void jogar(Posicao posicao, Carta carta) throws CartaNaoEstaNaMaoException, CartaInvalidaException, NaoEstaNaVezException;
    
    /**
     * Retorna a mão de um jogador.
     * @param posicao a posição do jogador
     * @return uma List de Carta com as cartas na mão do jogador perdido.
     */
    public List<Carta> getMao(Posicao posicao);

    /**
     * Adiciona o listener a uma lista de objetos que receberão notificação (evento) caso algo ocorra na partida
     * @param listenerPartida 
     * @sse ListenerPartida
     */
    public void addListener(ListenerPartida listenerPartida);
    
    /**
     * Cada jogador avisa que esta pronto. Quando todos estiverem, a partida começa.
     * @param posicao 
     */
    public void estaPronto(Posicao posicao);
    
    /**
     * Quantidade de pontos feitos por NS
     * Por exemplo, 8 vazas valem 2 pontos (book (6) + 2 = 8)
     * @return 
     * @see getVazasParaNS()
     */
    public int getPontosParaNS();
    
    /**
     * Quantidade de pontos feitos por EW
     * Por exemplo, 8 vazas valem 2 pontos (book (6) + 2 = 8)
     * @return 
     * @see getVazasParaEW()
     */
    public int getPontosParaEW();
    
    /**
     * 
     * Retorna o momento em que a partida começou.
     * 
     * @return o momento em que a partida começou
     */
    public Date getDataDeInicio();
    
    /**
     * Retorna uma cópia das cartas jogadas nessa trick
     * @return 
     */
    public Map<Posicao,Carta> getVaza(int nvaza);
    
    /** 
     * Primeiro jogador da trick atual.
     * @return a posição dele.
     */
    public Posicao getPrimeiroDaVaza(int nvaza);

    /**
     * De quem e a vez
     * @return a posicao do jogador que tem a vez.
     */
    public Posicao getVez(int nvaza);

    /**
     * O jogo já comecou?
     * @return se já iniciou
     */
    public boolean iniciou();

    public Posicao getVez();

    public Posicao getPrimeiroDaVaza();

    public Map<Posicao, Carta> getVaza();
}
