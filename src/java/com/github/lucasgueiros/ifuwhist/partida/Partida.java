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
public class Partida implements PartidaInterface {
    
    private Set<ListenerPartida> listeners = new HashSet<>();
    
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
    /** * a trick atual */
    private int numeroDaVaza = 0; // 
    /**
     * A partida já acabou?
     */
    private boolean acabou = false;
    
    // For tricks
    /** * De quem é a vez? */
    private Posicao vez;
    /** * Saidor da trick */
    private Posicao primeiroDaVaza;
    /** * as cartas dessa trick */
    private final Map<Posicao,Carta> vaza;
    
    // Deal
    /** * carta de trunfo! */
    private Carta trunfo; // 
    /** * as mãos dos jogadores */
    private final Map<Posicao,List<Carta>> maos; // 
    /** * o dealer da rodada*/
    private Posicao dador;
    private int pontosParaNS = 0;
    private int pontosParaEW = 0;
    
    /**
     * Contrutor padrão. Cria as estruturas de dados.
     */
    public Partida() {
        this.maos = new EnumMap<>(Posicao.class);
        this.vaza = new EnumMap<>(Posicao.class);
        maos.put(Posicao.NORTH, new LinkedList<>());
        maos.put(Posicao.EAST, new LinkedList<>());
        maos.put(Posicao.WEST, new LinkedList<>());
        maos.put(Posicao.SOUTH, new LinkedList<>());
    }

    public Partida(Mesa mesa, Posicao proximoNowDealer) {
        this();
        setMesa(mesa);
        setDealer(proximoNowDealer);
    }
    
    @Override
    public void jogar(Posicao posicao, Carta card) throws CartaNaoEstaNaMaoException, CartaInvalidaException, NaoEstaNaVezException  { // card to be played!
        // deve ser sua vez
        if(!vez.equals(posicao)) {
            throw new NaoEstaNaVezException();
        }
        // você deve ter a carta na mão
        if (!maos.get(vez).contains(card)) {
            throw new CartaNaoEstaNaMaoException();
        }
        
        // a carta deve ser do naipe corrente
        if (vaza.get(primeiroDaVaza) != null && card.getNaipe() != vaza.get(primeiroDaVaza).getNaipe()) {
            // caso não seja você não pode ter do naipe correte
            for (Carta c : maos.get(vez)) {
                if (c.getNaipe() == vaza.get(primeiroDaVaza).getNaipe()) {
                    throw new CartaInvalidaException(); // erro
                }
            }
        }
        // remova a carta da sua mão
        maos.get(vez).remove(card);
        
        // coloque a carta na trick
        this.vaza.put(vez, card);
        // mova a vez para o próximo jogador
        this.vez = this.vez.next();
        

        // se for a última carta, verifique o vencedor e inicie a próxima vaza
        if (this.vez == this.primeiroDaVaza) {
            trickEnded();
        }
        mudancaDeVez();
        // avise a table que alguém jogou
        this.mesa.turnChanged();
    }
    
    @Override
    public Naipe getNaipeDeTrunfo() {
       return trunfo.getNaipe();
    }

    @Override
    public Naipe getNaipeCorrente() {
        return vaza.get(primeiroDaVaza).getNaipe();
    }

    @Override
    public int getNumeroDaVaza() {
        return numeroDaVaza;
    }

    @Override
    public int getVazasParaNS() {
        return this.vazasParaNS;
    }

    @Override
    public int getVazasParaEW() {
        return this.numeroDaVaza - this.vazasParaNS;
    }

    @Override
    public int getNumeroDeCartas(Posicao posicao) {
        return maos.get(posicao).size();
    }

    @Override
    public boolean estaNaVezDe(Posicao posicao) {
        return vez.equals(posicao);
    }

    @Override
    public Carta getCartaDaVazaAtual(Posicao posicao) {
        return this.vaza.get(posicao);
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
        this.deal();
        this.start();
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
        listeners.add(e);
    }
    
    @Override
    public Date getDataDeInicio() {
        return dataDeInicio;
    }
    
    @Override
    public Posicao getPrimeiroDaVaza() {
        return primeiroDaVaza;
    }
    
    @Override
    public Map<Posicao,Carta> getVaza() {
        Map<Posicao,Carta> toReturn = new EnumMap<Posicao,Carta>(Posicao.class);
        for (Map.Entry<Posicao, Carta> entry : vaza.entrySet()) {
                toReturn.put(entry.getKey(), entry.getValue());
            }
        return toReturn;
    }
 
    private void start () {
        
        this.dataDeInicio = new Date();
        //this.resultado = null;
        this.acabou = false;
        
        this.primeiroDaVaza = dador.next();
        this.vez = this.primeiroDaVaza;
        //this.trickNumber++;
        this.mesa.turnChanged();
        this.mudancaDeVez();
    }
    
    private void deal() {
        dealV2((long) Math.random());
    }
    
    /**
     * Distribui as cartas. Emabaralha a partir de um random seed. E joga nas mãos.
     * Depois, ordena as cartas em cada mão
     * 
     * @param seed A "semente" de randomicidade a ser usada
     * 
     */
    private void dealV2(long seed){
        // limpe as mãos kkkkk
        for(Posicao po : Posicao.values()) {
            maos.get(po).clear();
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
                maos.get(p).add(set.poll());
            }
        }
        
        // ordene em cada mão
        for(Posicao p: Posicao.values())  {
        	maos.get(p).sort(new ComparaCartas());
        	
        }
        trunfo = maos.get(dador).get(  (int) (12 * r.nextDouble()) ); //ultima carta do dealer
    }
    
    /**
     * Distribui as cartas sorteando um valor entre 0 e 3 e, para cada um, coloca no jogador respectivo (0=Norte,1=Leste,etc.)
     * Tem uma tendência a deixar WEST com todos os Clubs.
     * Já deixa as cartas organizadas na mão.
     */
    private void dealV1() {
        for(Posicao po : Posicao.values()) {
            maos.get(po).clear();
        }
        
        for(Naipe su : Naipe.values()) {
            for(Simbolo sy : Simbolo.values()) {
                int random =  (int) ( Math.random() * 3 ) ; // um número entre 0 e 3
                loop:do {
                    switch(random) {
                        case 0: // NORTH
                            if(maos.get(Posicao.NORTH).size() < 13) {
                                maos.get(Posicao.NORTH).add(new Carta(sy,su));
                                break;
                            }
                        case 1: // EAST
                            if(maos.get(Posicao.EAST).size() < 13) {
                                maos.get(Posicao.EAST).add(new Carta(sy,su));
                                break;
                            }
                        case 2:// SOUTHn
                            if(maos.get(Posicao.SOUTH).size() < 13) {
                                maos.get(Posicao.SOUTH).add(new Carta(sy,su));
                                break;
                            }
                        case 3:// WEST
                            if(maos.get(Posicao.WEST).size() < 13) {
                                maos.get(Posicao.WEST).add(new Carta(sy,su));
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
     * Subrotina de play(Carta) acionada caso a carta jogada seja a última da
     * trick.
     */
    private void trickEnded() {
        Naipe trumphNaipe = trunfo.getNaipe();
        Naipe currentNaipe = vaza.get(primeiroDaVaza).getNaipe();
        Posicao winner = primeiroDaVaza;
        for (Posicao p : Posicao.values()) {
            Carta now = vaza.get(p);
            Carta won = vaza.get(winner);
            
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
        primeiroDaVaza = winner;
        //  e será a vez do primeiro jogador
        vez = primeiroDaVaza;
        // tire as cartas do trick
        vaza.clear();
        // de update
        // aumente os pontos de quem venceu
        if (winner == Posicao.SOUTH || winner == Posicao.NORTH) {
            vazasParaNS++;
        } // não precisa de ELSE porque os pontos de WEST e EAST são calculados a partir de trickNumber e tricksForNS
        
        // aumente a contagem de tricks
        numeroDaVaza++;
        
        // se tiver acabado o jogo!
        if (numeroDaVaza == 13) {
            // Esse código gerava erros para outras partes do sistema que tentavam acessar essas informações 
            // depois que a partida havia acabado. Não existe, por enquanto motivos para esse 
            // bloqueio. Entretanto, isso pode ser desejável futuramente.
            //first = null;
            //turn = null;
            
            // tire o book do vencedor e todos os pontos do perdedor
            pontosParaEW = 0; pontosParaNS = 0;
            if(vazasParaNS > 6) {
                pontosParaEW = vazasParaNS - 6;
            } else {
                pontosParaNS = 13 - vazasParaNS - 6;
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
    
    
    private Naipe getCurrentNaipe() {
        try {
            return vaza.get(this.primeiroDaVaza).getNaipe();
        } catch(NullPointerException e) {
            return null;
        }
    }

    private int getNowTrickNumber() {
        if(getTrickNumber() != 13) return getTrickNumber() + 1;
        else return 13;
    }
    
    private int getTricksForNS() {
        return vazasParaNS;
    }

    
    private int getTrickNumber() {
        return numeroDaVaza;
    }

    private int getTricksForEW() {
        return this.numeroDaVaza - this.vazasParaNS;
    }

    private int getNumberOfCartas(Posicao p) {
        if(p==null) return -1;
        List<Carta> hand = this.maos.get(p);
        return hand.size();
    }

    
    private Carta getPlayedCarta(Posicao p) {
        return this.vaza.get(p);
    }

    /**
     * Define a mesa na qual o jogo está sendo jogado.
     * @param aThis 
     */
    private final void setMesa(Mesa aThis) {
        this.mesa = aThis;
    }
 
    private List<Carta> getHand(Posicao position) {
        List<Carta> toReturn  = new LinkedList<>();
        for (Carta card : this.maos.get(position)) {
            toReturn.add(card);
        }
        return toReturn;
    }

    /**
     * Retorna o dealer da partida atual
     * @return posição do dealer
     */
    private Posicao getDealer() {
        return dador;
    }

    /**
     * Define a posição do dealer da partida atual
     * @param dealer a posição solicitada
     */
    private final void setDealer(Posicao dealer) {
        this.dador = dealer;
    }

    private boolean isAcabou() {
        return acabou;
    }

    private void acabou() {
        EventoPartida evento = new EventoPartida(this);
        for(ListenerPartida listener : this.listeners) {
            listener.partidaAcabou(evento);
        }
    }
    
    private void mudancaDeVez(){
        EventoPartida evento = new EventoPartida(this);
        evento.setVez(this.acabou ? null : vez);
        for(ListenerPartida listener : this.listeners) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    listener.alguemJogou(evento);
                }
            }).start();
            
        }
    }
    
}