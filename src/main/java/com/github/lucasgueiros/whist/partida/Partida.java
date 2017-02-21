/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.partida;

import com.github.lucasgueiros.whist.partida.eventos.PartidaInterface;
import com.github.lucasgueiros.whist.vaza.Vaza;
import com.github.lucasgueiros.whist.partida.eventos.ListenerPartida;
import com.github.lucasgueiros.whist.partida.eventos.RepetidorDeEventoPartida;
import com.github.lucasgueiros.whist.partida.excecoes.NaoEstaNaVezException;
import java.util.Date;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.bolsa.Bolsa;
import com.github.lucasgueiros.whist.bolsa.EmbaralhadorSimples;
import com.github.lucasgueiros.whist.vaza.Carta;
import com.github.lucasgueiros.whist.vaza.Naipe;
import com.github.lucasgueiros.whist.partida.excecoes.CartaInvalidaException;
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
    
    ///** * A mesa aonde está sendo jogada a Partida */
    //private Mesa mesa;
    
    // For score
    /** * quantas tricks NS já ganhou */
    private int vazasParaNS;
    private int vazasParaEW;
    private int [][] pontuacao = new int [13][2];
    
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
    
    private final Map<Posicao,Boolean> estaPronto = new EnumMap<>(Posicao.class);;
    private boolean todosEstaoProntos;

    
    /**
     * Contrutor padrão. Cria as estruturas de dados.
     */
    public Partida() {
        //estaPronto = new EnumMap<>(Posicao.class);
        this.todosEstaoProntos = false;
        this.maos = new EnumMap<>(Posicao.class);
        this.vazas = new Vaza[13];
        maos.put(Posicao.NORTH, new LinkedList<>());
        maos.put(Posicao.EAST, new LinkedList<>());
        maos.put(Posicao.WEST, new LinkedList<>());
        maos.put(Posicao.SOUTH, new LinkedList<>());
        for(Posicao p : Posicao.values()){
            estaPronto.put(p, false);
        }
        this.repetidor = new RepetidorDeEventoPartida(this);
    }

    public Partida(Posicao proximoNowDealer) {
        this();
        //this.mesa = mesa;
        this.dador = proximoNowDealer;
        //this.repetidor = new RepetidorDeEventoPartida(this);
    }
    
    @Override
    public void jogar(Posicao posicao, Carta card) throws CartaNaoEstaNaMaoException, CartaInvalidaException, NaoEstaNaVezException  { // card to be played!
        if(!todosEstaoProntos) return;
        // Pegue a vaza atual
        Vaza vaza = vazas[numeroDaVaza];    
        boolean vazaAcabou = false;
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
                pontuacao[numeroDaVaza][0]++;
                vazasParaNS++; // pontua para NS
            } else {
                pontuacao[numeroDaVaza][1]++;
                vazasParaEW++; // pontua para EW
            }
            vazaAcabou = true;
            repetidor.vazaAcabou();
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
                pontuacao[numeroDaVaza][0] = pontuacao[numeroDaVaza-1][0];
                pontuacao[numeroDaVaza][1] = pontuacao[numeroDaVaza-1][1];
            }
        } 
        // avise ao mundo que mudou de vez
        repetidor.mudancaDeVez(vazaAcabou);
    }
    
    @Override
    public Naipe getNaipeDeTrunfo() {
        if(!todosEstaoProntos) return null;
       return trunfo;
    }

    @Override
    public Naipe getNaipeCorrente() {
        if(!todosEstaoProntos) return null;
        return this.vazas[numeroDaVaza].getCorrente();
    }
    
    public Naipe getNaipeCorrente(int vaza) {
        if(!todosEstaoProntos) return null;
        return this.vazas[vaza].getCorrente();
    }

    @Override
    public int getNumeroDaVaza() {
        return numeroDaVaza + 1;
    }

    public int getVazasParaNS() {
        return this.vazasParaNS;
    }
    
    public int getVazasParaNS(int vaza) {
        return pontuacao[vaza][0];
    }

    public int getVazasParaEW() {
        return this.vazasParaEW;
    }
    
    public int getVazasParaEW(int vaza) {
        return pontuacao[vaza][1];
    }

    public int getNumeroDeCartas(Posicao posicao) {
        if(!todosEstaoProntos) return 0;
        return maos.get(posicao).size();
    }

    public int getNumeroDeCartas(int vaza,Posicao posicao) {
        if(!todosEstaoProntos) return 0;
        if(vaza < numeroDaVaza) return 12 - vaza;
        return maos.get(posicao).size();
    }
    
    @Override
    public boolean estaNaVezDe(Posicao posicao) {
        if(!todosEstaoProntos) return false;
        return this.vazas[numeroDaVaza].getVez().equals(posicao);
    }

    public Carta getCartaDaVazaAtual(Posicao posicao) {
        if(!todosEstaoProntos) return null;
        Vaza vaza = this.vazas[numeroDaVaza];
        Map<Posicao,Carta> cartas = vaza.getCartas();
        return cartas.get(posicao);
    }

    public Carta getCartaDaVaza(int nvaza, Posicao posicao) {
        if(!todosEstaoProntos) return null;
        Vaza vaza = this.vazas[nvaza];
        if(vaza==null) return null;
        Map<Posicao,Carta> cartas = vaza.getCartas();
        return cartas.get(posicao);
    }
    
    @Override
    public List<Carta> getMao(Posicao posicao) {
        if(!todosEstaoProntos) return null;
        List<Carta> toReturn  = new LinkedList<>();
        for (Carta card : this.maos.get(posicao)) {
            toReturn.add(card);
        }
        return toReturn;
    }

    private void iniciar() {
        this.dataDeInicio = new Date();
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
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 13; j++) {
                pontuacao[j][i] = 0;
            }
        }
        // avise que "mudou de vez" (de vez de ninguém -> vez de alguém)
        repetidor.mudancaDeVez(true);
    }

    @Override
    public int getPontosParaNS() {
        return this.vazasParaNS - 6 >= 0 ? this.vazasParaNS - 6 : 0;
    }

    @Override
    public int getPontosParaEW() {
        return this.getVazasParaEW() - 6 >= 0 ? this.getVazasParaEW() - 6 : 0;
    }
    
    /*@Override
    public Mesa getMesa() {
        return mesa;
    }*/

    @Override
    public void addListener(ListenerPartida e) {
        repetidor.adicionar(e);
    }
    
    @Override
    public Date getDataDeInicio() {
        return dataDeInicio;
    }
    
    public Posicao getPrimeiroDaVaza() {
        if(!todosEstaoProntos) return null;
        return vazas[numeroDaVaza].getPrimeiro();
    }
    
    public Posicao getPrimeiroDaVaza(int vaza) {
        if(!todosEstaoProntos) return null;
        return vazas[vaza].getPrimeiro();
    }
    
    public Map<Posicao,Carta> getVaza() {
        if(!todosEstaoProntos) return null;
        return this.vazas[numeroDaVaza].getCartas();
    }
    
    public Map<Posicao,Carta> getVaza(int vaza) {
        if(!todosEstaoProntos) return null;
        return this.vazas[vaza].getCartas();
    }

    public Posicao getVez() {
        if(!todosEstaoProntos) return null;
        return vazas[numeroDaVaza].getVez();
    }

    public Posicao getVez(int vaza) {
        if(!todosEstaoProntos || vaza != numeroDaVaza) return null;
        return vazas[numeroDaVaza].getVez();
    }
    
    @Override
    public boolean iniciou() {
        return todosEstaoProntos;//iniciou;
    }
    
    @Override
    public void estaPronto(Posicao posicao) {
        if(estaPronto.get(posicao)){
            return;
        }
        estaPronto.put(posicao, true);
        todosEstaoProntos = true;
        for(Posicao p : Posicao.values()){
            todosEstaoProntos = todosEstaoProntos && estaPronto.get(p);
        }
        if(todosEstaoProntos){
            iniciar();
        }
    }
    
}
