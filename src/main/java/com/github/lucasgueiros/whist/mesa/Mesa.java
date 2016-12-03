/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.mesa;

import com.github.lucasgueiros.whist.jogador.Jogador;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lucas
 */

public class Mesa {
    
    private long id;
    
    private Map<Posicao,Jogador> players = new HashMap<>();
    private Posicao nowDealer = Posicao.WEST;
    private boolean temJogadoresFalsos;
   
    public Mesa(Jogador north, Jogador south, Jogador east, Jogador west ) {
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

    public boolean temJogadoresFalsos() {
        return temJogadoresFalsos;
    }
    
    
}
