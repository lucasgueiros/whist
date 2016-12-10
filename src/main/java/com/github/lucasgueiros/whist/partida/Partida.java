/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.partida;

import com.github.lucasgueiros.whist.partida.eventos.PartidaInterface;
import com.github.lucasgueiros.whist.partida.vaza.Vaza;
import com.github.lucasgueiros.whist.partida.eventos.ListenerPartida;
import com.github.lucasgueiros.whist.partida.eventos.RepetidorDeEventoPartida;
import com.github.lucasgueiros.whist.partida.excecoes.NaoEstaNaVezException;
import java.util.Date;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.lucasgueiros.whist.mesa.Mesa;
import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.bolsa.Bolsa;
import com.github.lucasgueiros.whist.partida.bolsa.EmbaralhadorSimples;
import com.github.lucasgueiros.whist.partida.vaza.Carta;
import com.github.lucasgueiros.whist.partida.vaza.Naipe;
import com.github.lucasgueiros.whist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.whist.partida.excecoes.CartaNaoEstaNaMaoException;
import com.github.lucasgueiros.whist.util.aletoriedade.GeradorPadrao;

/**
 *
 * Essa classe manipula todas a lógica do jogo mas não implementa os jogadores robóticos.
 * 
 * @author lucas
 */
public class Partida implements PartidaInterface {
    
    // General
    /** * O resultado do jogo */
    //private Resultado resultado = null;
    /** * Momento do início do jogo. */
    private Date dataDeInicio;
    
    /** * A mesa aonde está sendo jogada a Partida */
    private Mesa mesa;
    
    // For score
    /** * quantas tricks NS já ganhou */
    private int vazasParaNS;
    private int vazasParaEW;
    /** * a trick atual */
    private int numeroDaVaza = 0; // 
    /**
     * A partida já acabou?
     */
    private boolean acabou = false;
    
    
    private Vaza [] vazas;
    private Bolsa bolsa;
    
    /** * carta de trunfo! */
    private Naipe trunfo; // 
    /** * as mãos dos jogadores */
    private final Map<Posicao,List<Carta>> maos; // 
    /** * o dealer da rodada*/
    private Posicao dador;
    
    private RepetidorDeEventoPartida repetidor;
    
    /**
     * Contrutor padrão. Cria as estruturas de dados.
     */
    public Partida() {
        this.maos = new EnumMap<>(Posicao.class);
        this.vazas = new Vaza[13];
        maos.put(Posicao.NORTH, new LinkedList<>());
        maos.put(Posicao.EAST, new LinkedList<>());
        maos.put(Posicao.WEST, new LinkedList<>());
        maos.put(Posicao.SOUTH, new LinkedList<>());
    }

    public Partida(Mesa mesa, Posicao proximoNowDealer) {
        this();
        this.mesa = mesa;
        this.dador = proximoNowDealer;
        this.repetidor = new RepetidorDeEventoPartida(this);
    }
    
    @Override
    public void jogar(Posicao posicao, Carta card) throws CartaNaoEstaNaMaoException, CartaInvalidaException, NaoEstaNaVezException  { // card to be played!
        // Pegue a vaza atual
        Vaza vaza = vazas[numeroDaVaza];        
        // deve ser sua vez
        if(!vaza.getVez().equals(posicao)) {
            throw new NaoEstaNaVezException();
        }
        // você deve ter a carta na mão
        if (!maos.get(posicao).contains(card)) {
            throw new CartaNaoEstaNaMaoException();
        }
        
        // a carta deve ser do naipe corrente
        Naipe corrente = vaza.getCorrente();
        if(corrente !=null && ! card.getNaipe().equals(corrente)) {
            // caso não seja você não pode ter do naipe correte
            for (Carta c : maos.get(vaza.getVez())) {
                // se existe uma carta do naipe corrente, temos problemas
                if (c.getNaipe().equals(corrente)) {
                    throw new CartaInvalidaException(); // erro
                }
            }
        }
        
        // remova a carta da sua mão
        maos.get(posicao).remove(card);
        
        // jogue na vaza
        vaza.jogar(card);
        
        if(vaza.acabou()) { // se a vaza acabou

            Posicao ganhador = vaza.getGanhador();
            if (ganhador == Posicao.SOUTH || ganhador == Posicao.NORTH) {
                vazasParaNS++; // pontua para NS
            } else {
                vazasParaEW++; // pontua para EW
            }
            if(this.getNumeroDaVaza()==13){
                // se tiver acabado o jogo!
                this.acabou = true;
                // envie a mensagem dizendo que acabou o jogo
                repetidor.acabou();
            } else { // se não acabou
                // o saidor da próxima vaza é o vencedor da última
                Vaza proximaVaza = new Vaza(ganhador, trunfo);
                // passe para a proxima vaza e adicione-a ao array.
                vazas[++numeroDaVaza] = proximaVaza;
            }
        } 
        // avise ao mundo que mudou de vez
        repetidor.mudancaDeVez();
    }
    
    @Override
    public Naipe getNaipeDeTrunfo() {
       return trunfo;
    }

    @Override
    public Naipe getNaipeCorrente() {
        return this.vazas[numeroDaVaza].getCorrente();
    }

    @Override
    public int getNumeroDaVaza() {
        return numeroDaVaza + 1;
    }

    @Override
    public int getVazasParaNS() {
        return this.vazasParaNS;
    }

    @Override
    public int getVazasParaEW() {
        return this.vazasParaEW;
    }

    @Override
    public int getNumeroDeCartas(Posicao posicao) {
        return maos.get(posicao).size();
    }

    @Override
    public boolean estaNaVezDe(Posicao posicao) {
        return this.vazas[numeroDaVaza].getVez().equals(posicao);
    }

    @Override
    public Carta getCartaDaVazaAtual(Posicao posicao) {
        return this.vazas[numeroDaVaza].getCartas().get(posicao);
    }

    @Override
    public List<Carta> getMao(Posicao posicao) {
        List<Carta> toReturn  = new LinkedList<>();
        for (Carta card : this.maos.get(posicao)) {
            toReturn.add(card);
        }
        return toReturn;
    }

    @Override
    public void iniciar() {
        // embaralhe e crie a bolsa
        bolsa = new EmbaralhadorSimples().embaralhar(dador, GeradorPadrao.getGerador());
        // distribua as cartas
        for (Posicao posicao : Posicao.values()) {
            maos.get(posicao).addAll(bolsa.getMao(posicao));
        }
        // veja o naipe de trunfo
        this.trunfo = bolsa.getTrunfo();
        // marque a data de início
        this.dataDeInicio = new Date();
        // ainda não acabou
        this.acabou = false;
        // em qual vaza está - 1
        numeroDaVaza = 0;
        // crie a primeira vaza
        vazas[0] = new Vaza(dador.next(), trunfo);
        // avise que "mudou de vez" (de vez de ninguém -> vez de alguém)
        repetidor.mudancaDeVez();
    }

    @Override
    public int getPontosParaNS() {
        return this.vazasParaNS - 6 >= 0 ? this.vazasParaNS - 6 : 0;
    }

    @Override
    public int getPontosParaEW() {
        return this.getVazasParaEW() - 6 >= 0 ? this.getVazasParaEW() - 6 : 0;
    }
 
    @Override
    public Mesa getMesa() {
        return mesa;
    }

    @Override
    public void addListener(ListenerPartida e) {
        repetidor.adicionar(e);
    }
    
    @Override
    public Date getDataDeInicio() {
        return dataDeInicio;
    }
    
    @Override
    public Posicao getPrimeiroDaVaza() {
        return vazas[numeroDaVaza].getPrimeiro();
    }
    
    @Override
    public Map<Posicao,Carta> getVaza() {
        return this.vazas[numeroDaVaza].getCartas();
    }

    @Override
    public Posicao getVez() {
        return vazas[numeroDaVaza].getVez();
    }
    
}