/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.bolsa;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.vaza.Carta;
import com.github.lucasgueiros.whist.vaza.ComparaCartas;
import com.github.lucasgueiros.whist.vaza.Naipe;
import com.github.lucasgueiros.whist.vaza.Simbolo;
import com.github.lucasgueiros.whist.util.aletoriedade.GeradorAleatorio;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class EmbaralhadorSimples implements Embaralhador {

    @Override
    public Bolsa embaralhar(Posicao dealer, GeradorAleatorio geradorAleatorio){
        //long seed = (long) Math.random();
        
        Carta [] maoDealer = new Carta[13];
        Carta [] maoEsquerda = new Carta[13];
        Carta [] maoDireita = new Carta[13];
        Carta [] maoParceiro = new Carta[13];
        
        // agora crie um baralho 
        LinkedList<Carta> set = new LinkedList<>();
        for (Naipe su: Naipe.values()) {
            for (Simbolo sy : Simbolo.values()) {
                set.add(Carta.getCarta(su,sy));
            }
        }
        
        //Random r = new Random(seed);
        // embaralhe
        Collections.shuffle(set, new Random(){
            private int [] alea = geradorAleatorio.get(52,0,51,-1);
            
            @Override
            public int nextInt(int bound) {
                return alea[bound-1];
            }
        });
        
        // distribua
        // até 13
        for (int i = 0; i < 13; i++) {
            maoEsquerda[i] = set.poll();
            maoDealer[i] = set.poll();
            maoDireita[i] = set.poll();
            maoParceiro[i] = set.poll();
        }
        
        Naipe trunfo = maoDealer[12].getNaipe(); //ultima carta do dealer
        
        // ordene em cada mão
        Arrays.sort(maoDealer, new ComparaCartas());
        Arrays.sort(maoEsquerda, new ComparaCartas());
        Arrays.sort(maoParceiro, new ComparaCartas());
        Arrays.sort(maoDireita, new ComparaCartas());
        
        switch(dealer) {
            case NORTH: return new Bolsa(trunfo, maoDealer, maoEsquerda, maoParceiro, maoDireita, dealer);
            case WEST: return new Bolsa(trunfo, maoEsquerda, maoParceiro, maoDireita, maoDealer, dealer);
            case SOUTH: return new Bolsa(trunfo, maoParceiro, maoDireita, maoDealer, maoEsquerda, dealer);
            case EAST: return new Bolsa(trunfo, maoDireita, maoDealer, maoEsquerda, maoParceiro, dealer);
            default: return null;
        }
        
    }
    
}
