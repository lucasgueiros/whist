/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.mesa;

import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import java.util.HashMap;
import java.util.Map;

import com.github.lucasgueiros.ifuwhist.usuario.Usuario;

/**
 *
 * @author lucas
 */

public class Mesa {
    
    private long id;
    
    private Map<Posicao,Jogador> players = new HashMap<>();
    private Posicao nowDealer = Posicao.WEST;
    private boolean temJogadoresFalsos;
    
    public Mesa(Usuario north, Usuario south, Usuario east, Usuario west ) {
        if(north==null) {
            throw new NullPointerException("north==null");
        }else if(south==null) {
            throw new NullPointerException("south==null");
        }else if(east==null) {
            throw new NullPointerException("east==null");
        } else if(west==null) {
            throw new NullPointerException("west==null");
        }
        this.players.put(Posicao.EAST, east);
        this.players.put(Posicao.NORTH, north);
        this.players.put(Posicao.SOUTH, south);
        this.players.put(Posicao.WEST, west);
    }
    
    //public Posicao addJogador(Usuario p) {
    //    Posicao r = null;
    //    for(Posicao po : Posicao.values()) {
    //        if(players.get(po).isFake()) {
    //            players.replace(po, p);
    //            r = po;
    //            break;
    //        }
    //    }
    //    return r;
    //}
    
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    //public boolean hasPlace() {
    //    boolean hasnot = true;
    //    for(Posicao po : Posicao.values()) {
    //        if(this.players.get(po).isFake()) hasnot = false;
    //    }
    //    return !hasnot;
    //}
 
    public Posicao getPosicao(Jogador p) {
        for(Posicao po : Posicao.values()) {
            if(players.get(po).equals(p))
                return po;
        }
        return  null;
    }

    public Posicao getProximoNowDealer() {
    	nowDealer = nowDealer.next();
    	return nowDealer;
    }
    
    /*public void start() {
        nowDealer = nowDealer.next();
        running = new Partida();
        running.setMesa(this);
        running.setDealer(nowDealer);
        running.deal();
        running.start();
    }
    
    public void start(long seed) {
        nowDealer = nowDealer.next();
        running = new Partida();
        running.setMesa(this);
        running.setDealer(nowDealer);
        running.dealV2(seed);
        running.start();
    }*/

    public Jogador getJogador(Posicao p) {
        return this.players.get(p);
    }

    public void turnChanged() {
        /*Jogador p = this.players.get(running.getTurn());
        if(p.isFake()) {
            FakeJogador fp = (FakeJogador) p;
            // atualize o fake player com as jogadas!
            Card c = fp.yourTurn(running.getTrick(),running.getFirst(),running.getTrumph().getSuit(),running.getHand(running.getTurn()));
    //        running.play(c);
        }*/
    }
    
    /*@Deprecated
    public void updateLastPartida() {
        this.lastPartida = this.running.getPartidaTerminada();
        this.lastPartida.setEast(this.getJogador(Posicao.EAST));
        this.lastPartida.setNorth(this.getJogador(Posicao.NORTH));
        this.lastPartida.setSouth(this.getJogador(Posicao.SOUTH));
        this.lastPartida.setWest(this.getJogador(Posicao.WEST));
    }*/

    public boolean temJogadoresFalsos() {
        return temJogadoresFalsos;
    }
    
    
}
