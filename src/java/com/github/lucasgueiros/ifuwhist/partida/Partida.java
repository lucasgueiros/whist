/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida;


import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.github.lucasgueiros.ifuwhist.mesa.Mesa;
import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Carta;
import com.github.lucasgueiros.ifuwhist.partida.cartas.ComparaCartas;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Naipe;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Simbolo;
import com.github.lucasgueiros.ifuwhist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.ifuwhist.partida.excecoes.CartaNaoEstaNaMaoException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Essa classe manipula todas a lógica do jogo mas não implementa os jogadores robóticos.
 * 
 * @author lucas
 */
public class Partida {
    
    private Set<ListenerPartida> listeners = new HashSet<>();
    
    // General
    /** * O resultado do jogo */
    //private Resultado resultado = null;
    /** * Momento do início do jogo. */
    private Date inicial;
    
    /** * A mesa aonde está sendo jogada a Partida */
    private Mesa mesa;
    
    // For score
    /** * quantas tricks NS já ganhou */
    private int tricksForNS;
    /** * a trick atual */
    private int trickNumber = 0; // 
    /**
     * A partida já acabou?
     */
    private boolean acabou = false;
    
    // For tricks
    /** * De quem é a vez? */
    private Posicao turn;
    /** * Saidor da trick */
    private Posicao first;
    /** * as cartas dessa trick */
    private final Map<Posicao,Carta> trick;
    
    // Deal
    /** * carta de trunfo! */
    private Carta trumph; // 
    /** * as mãos dos jogadores */
    private final Map<Posicao,List<Carta>> hands; // 
    /** * o dealer da rodada*/
    private Posicao dealer;
    private int pointsEW = 0;
    private int pointsNS = 0;
    
    /**
     * Contrutor padrão. Cria as estruturas de dados.
     */
    public Partida() {
        this.hands = new EnumMap<>(Posicao.class);
        this.trick = new EnumMap<>(Posicao.class);
        hands.put(Posicao.NORTH, new LinkedList<Carta>());
        hands.put(Posicao.EAST, new LinkedList<Carta>());
        hands.put(Posicao.WEST, new LinkedList<Carta>());
        hands.put(Posicao.SOUTH, new LinkedList<Carta>());
    }
    
    /**
     * Configura o jogo para iniciar.
     * Marca o momento de início.
     * Apaga o objeto Partida.
     * Recomenda-se embaralhar cartas antes!
     * @see dealV1 dealV2
     */
    public void start () {
        
        this.inicial = new Date();
        //this.resultado = null;
        this.acabou = false;
        
        this.first = dealer.next();
        this.turn = this.first;
        //this.trickNumber++;
        this.mesa.turnChanged();
    }
    
    /**
     * Distribui as cartas. Emabaralha a partir de um random seed randômico. E joga nas mãos.
     * Depois, ordena as cartas em cada mão.
     * 
     */
    public void deal() {
        dealV2((long) Math.random());
    }
    
    /**
     * Distribui as cartas. Emabaralha a partir de um random seed. E joga nas mãos.
     * Depois, ordena as cartas em cada mão
     * 
     * @param seed A "semente" de randomicidade a ser usada
     * 
     */
    public void dealV2(long seed){
        // limpe as mãos kkkkk
        for(Posicao po : Posicao.values()) {
            hands.get(po).clear();
        }
        
        // agora crie um baralho 
        LinkedList<Carta> set = new LinkedList<>();
        for (Naipe su: Naipe.values()) {
            for (Simbolo sy : Simbolo.values()) {
                set.add(new Carta(su, sy));
            }
        }
        
        Random r = new Random(seed);
        // embaralhe
        Collections.shuffle(set, r);
        
        // distribua
        // até 13
        for (int i = 0; i < 13; i++) {
            for(Posicao p: Posicao.values())  {
                hands.get(p).add(set.poll());
            }
        }
        
        // ordene em cada mão
        for(Posicao p: Posicao.values())  {
        	hands.get(p).sort(new ComparaCartas());
        	
        }
        trumph = hands.get(dealer).get(  (int) (12 * r.nextDouble()) ); //ultima carta do dealer
    }
    
    /**
     * Distribui as cartas sorteando um valor entre 0 e 3 e, para cada um, coloca no jogador respectivo (0=Norte,1=Leste,etc.)
     * Tem uma tendência a deixar WEST com todos os Clubs.
     * Já deixa as cartas organizadas na mão.
     */
    public void dealV1() {
        for(Posicao po : Posicao.values()) {
            hands.get(po).clear();
        }
        
        for(Naipe su : Naipe.values()) {
            for(Simbolo sy : Simbolo.values()) {
                int random =  (int) ( Math.random() * 3 ) ; // um número entre 0 e 3
                loop:do {
                    switch(random) {
                        case 0: // NORTH
                            if(hands.get(Posicao.NORTH).size() < 13) {
                                hands.get(Posicao.NORTH).add(new Carta(sy,su));
                                break;
                            }
                        case 1: // EAST
                            if(hands.get(Posicao.EAST).size() < 13) {
                                hands.get(Posicao.EAST).add(new Carta(sy,su));
                                break;
                            }
                        case 2:// SOUTHn
                            if(hands.get(Posicao.SOUTH).size() < 13) {
                                hands.get(Posicao.SOUTH).add(new Carta(sy,su));
                                break;
                            }
                        case 3:// WEST
                            if(hands.get(Posicao.WEST).size() < 13) {
                                hands.get(Posicao.WEST).add(new Carta(sy,su));
                                break;
                            } else {
                                continue loop; // volte para tentar dar a carta para os outros
                            }
                    }
                } while (false);
            }
        }
    }
    
    /**
     * Recebe uma jogada, analisa-a de acordo com as regras do Whist e gera as consequências.
     * Não se preocupe caso uma exceção seja lançada, apenas avise o usuário e continue a jogar.
     * @param card a carta jogada
     * @throws com.github.lucasgueiros.partida.excecoes.CartaNaoEstaNaMaoException
     * @throws com.github.lucasgueiros.partida.excecoes.CartaInvalidaException
     */
    public void play(Carta card) throws CartaNaoEstaNaMaoException, CartaInvalidaException  { // card to be played!
        // você deve ter a carta na mão
        if (!hands.get(turn).contains(card)) {
            throw new CartaNaoEstaNaMaoException();
        }
        
        // a carta deve ser do naipe corrente
        if (trick.get(first) != null && card.getNaipe() != trick.get(first).getNaipe()) {
            // caso não seja você não pode ter do naipe correte
            for (Carta c : hands.get(turn)) {
                if (c.getNaipe() == trick.get(first).getNaipe()) {
                    throw new CartaInvalidaException(); // erro
                }
            }
        }
        // remova a carta da sua mão
        hands.get(turn).remove(card);
        
        // coloque a carta na trick
        this.trick.put(turn, card);
        // mova a vez para o próximo jogador
        this.turn = this.turn.next();

        // se for a última carta, verifique o vencedor e inicie a próxima vaza
        if (this.turn == this.first) {
            trickEnded();
        }
        // avise a table que alguém jogou
        this.mesa.turnChanged();
    }

    /**
     * Subrotina de play(Carta) acionada caso a carta jogada seja a última da
     * trick.
     */
    private void trickEnded() {
        Naipe trumphNaipe = trumph.getNaipe();
        Naipe currentNaipe = trick.get(first).getNaipe();
        Posicao winner = first;
        for (Posicao p : Posicao.values()) {
            Carta now = trick.get(p);
            Carta won = trick.get(winner);
            
            if (won.getNaipe().equals(trumphNaipe) 
                    && now.getNaipe().equals(trumphNaipe)
                    && now.getSimbolo().compareTo(won.getSimbolo()) < 0) {
                winner = p;
            // caso o vencedor tenha usando corrente
            // e eu tenha tentado corrente também
            // e a minha carta seja maior que a deçe
            } else if (won.getNaipe().equals(currentNaipe)
                    && now.getNaipe().equals(currentNaipe)
                    && now.getSimbolo().compareTo(won.getSimbolo()) < 0) {
                winner = p;
            // caso o vencedor tenha usando corrente
            // e eu tenha trunfo!
            } else if ( ! currentNaipe.equals(trumphNaipe)
                    && won.getNaipe().equals(currentNaipe)
                    && now.getNaipe().equals(trumphNaipe)) {
                winner = p;
            }
        }

        
        //  o saidor da próxima vaza é o vencedor da última
        first = winner;
        //  e será a vez do primeiro jogador
        turn = first;
        // tire as cartas do trick
        trick.clear();
        // de update
        // aumente os pontos de quem venceu
        if (winner == Posicao.SOUTH || winner == Posicao.NORTH) {
            tricksForNS++;
        } // não precisa de ELSE porque os pontos de WEST e EAST são calculados a partir de trickNumber e tricksForNS
        
        // aumente a contagem de tricks
        trickNumber++;
        
        // se tiver acabado o jogo!
        if (trickNumber == 13) {
            // Esse código gerava erros para outras partes do sistema que tentavam acessar essas informações 
            // depois que a partida havia acabado. Não existe, por enquanto motivos para esse 
            // bloqueio. Entretanto, isso pode ser desejável futuramente.
            //first = null;
            //turn = null;
            
            // tire o book do vencedor e todos os pontos do perdedor
            pointsNS = 0; pointsEW = 0;
            if(tricksForNS > 6) {
                pointsNS = tricksForNS - 6;
            } else {
                pointsEW = 13 - tricksForNS - 6;
            }
            this.acabou = true;
            //this.resultado = new Resultado(inicial, new Date(), pointsNS, pointsEW);
            //this.mesa.setLastPartida(resultado);
            //if(!this.mesa.temJogadoresAutomaticos()){
            //    this.resultado.setNorth((Usuario)this.mesa.getJogador(Posicao.NORTH));
            //    this.resultado.setEast((Usuario)this.mesa.getJogador(Posicao.EAST));
            //    this.resultado.setSouth((Usuario)this.mesa.getJogador(Posicao.SOUTH));
            //    this.resultado.setWest((Usuario)this.mesa.getJogador(Posicao.WEST));
            //}
            
            //this.mesa.updateLastPartida();
        }
        
    }
    
    /*
     * Retorna o resultado do j.ogo. É colocado o valor null quando o jogo inicia e só deixa de ser null quando ele acaba.
     * @return o objeto Partida com os dados da partida.
     */
    //public Resultado getPartidaTerminada() {
    //    return resultado;
    //}
    
    /*
     * Retorna as cartas na mão de um jogador de um naipe. Ainda não foi implementado.
     * @param p a posição do jogador
     * @param s o naipe que se deseja.
     * @return retorna em formato de String.
     */
    /*private String getHand(Posicao p, Naipe s) {
        return null;
    }*/

    
    /**
     * 
     * Retorna o momento em que a partida começou.
     * 
     * @return o momento em que a partida começou
     */
    public Date getInicial() {
        return inicial;
    }

    /** 
     * De quem é a vez.
     * @return a Posição do jogador que deve jogar agora.
     */
    public Posicao getTurn() {
        return turn;
    }

    /** 
     * Primeiro jogador da trick atual.
     * @return a posição dele.
     */
    public Posicao getFirst() {
        return first;
    }

    /**
     * A carta de trunfo dessa partida
     * @return o objeto Carta correspondente.
     */
    public Carta getTrumph() {
        return trumph;
    }

    /**
     * O naipe corrente dessa vaza.
     * @return o objeto Naipe correspondente.
     */
    public Naipe getCurrentNaipe() {
        try {
            return trick.get(this.first).getNaipe();
        } catch(NullPointerException e) {
            return null;
        }
    }

    /**
     * Número da vaza atual.
     * Normalmente é igual a getTrickNumber() + 1. Exceto quando o jogo já tiver acabado.
     * 
     * @return a quantidade de vazas feitas.
     * @see getNowTricksForEW()
     */
    public int getNowTrickNumber() {
        if(getTrickNumber() != 13) return getTrickNumber() + 1;
        else return 13;
    }
    
    /**
     * Quantas vazas a dupla N-S ganhou.
     * 
     * @return a quantidade de vazas feitas.
     * @see getNowTricksForEW()
     */
    public int getTricksForNS() {
        return tricksForNS;
    }

    /**
     * Quantas vazas já passaram.
     * Perceba que esse não é o número da vaza atual.
     * 
     * @return a quantidade de vazas feitas.
     * @see getNowTrickNumber()
     */
    public int getTrickNumber() {
        return trickNumber;
    }

    /**
     * Quantas vazas a dupla E-W ganhou.
     * 
     * @return a quantidade de vazas feitas.
     * @see getNowTricksForNS()
     */
    public int getTricksForEW() {
        return this.trickNumber - this.tricksForNS;
    }

    /**
     * Retorna o número de cartas na mão de um jogador
     * @param p é o jogador
     * @return um int que diz a quantidade de cartas na mão dele.
     */
    public int getNumberOfCartas(Posicao p) {
        if(p==null) return -1;
        List<Carta> hand = this.hands.get(p);
        return hand.size();
    }

    /**
     * Retorna a carta jogada na vaza atual pelo jogador na posição p.
     * @param p a posição do jogador
     * @return a carta que ele jogou.s
     */
    public Carta getPlayedCarta(Posicao p) {
        return this.trick.get(p);
    }

    /**
     * Define a mesa na qual o jogo está sendo jogado.
     * @param aThis 
     */
    public void setMesa(Mesa aThis) {
        this.mesa = aThis;
    }
 
    /**
     * Retorna uma cópia das cartas jogadas nessa trick
     * @return 
     */
    public Map<Posicao,Carta> getTrick() {
        Map<Posicao,Carta> toReturn = new EnumMap<Posicao,Carta>(Posicao.class);
        for (Map.Entry<Posicao, Carta> entry : trick.entrySet()) {
                //Posicao position = entry.getKey();
                Carta card = entry.getValue();
                toReturn.put(turn, card);
            }
        return toReturn;
    }

    /**
     * Retorna a mão de um jogador.
     * @param position a posição do jogador
     * @return uma List de Carta com as cartas na mão do jogador perdido.
     */
    public List<Carta> getHand(Posicao position) {
        List<Carta> toReturn  = new LinkedList<>();
        for (Carta card : this.hands.get(position)) {
            toReturn.add(card);
        }
        return toReturn;
    }

    /**
     * Retorna o dealer da partida atual
     * @return posição do dealer
     */
    public Posicao getDealer() {
        return dealer;
    }

    /**
     * Define a posição do dealer da partida atual
     * @param dealer a posição solicitada
     */
    public void setDealer(Posicao dealer) {
        this.dealer = dealer;
    }

    public boolean isAcabou() {
        return acabou;
    }

    public int getPointsEW() {
        return pointsEW;
    }

    public int getPointsNS() {
        return pointsNS;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public boolean addListener(ListenerPartida e) {
        return listeners.add(e);
    }
    
    public void acabou() {
        EventoPartida evento = new EventoPartida(this);
        for(ListenerPartida listener : this.listeners) {
            listener.partidaAcabou(evento);
        }
    }
    
}

