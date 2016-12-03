/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.bolsa;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.vaza.Carta;
import com.github.lucasgueiros.whist.partida.vaza.ComparaCartas;
import com.github.lucasgueiros.whist.partida.vaza.Naipe;
import com.github.lucasgueiros.whist.partida.vaza.Simbolo;
import com.github.lucasgueiros.whist.util.aletoriedade.GeradorAleatorio;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author lucas
 */
public class EmbaralhadorOestePorUltimo implements Embaralhador {

    @Override
    public Bolsa embaralhar(Posicao dealer, GeradorAleatorio gerador) {
        List<Carta> maoDealer = new LinkedList<>();
        List<Carta> maoEsquerda = new LinkedList<>();
        List<Carta> maoDireita = new LinkedList<>();
        List<Carta> maoParceiro = new LinkedList<>();
        
        for(Naipe su : Naipe.values()) {
            for(Simbolo sy : Simbolo.values()) {
                int random =  gerador.get(1,0,3)[0] ; // um número entre 0 e 3
                loop:do {
                    switch(random) {
                        case 0: // NORTH
                            if(maoDealer.size() < 13) {
                                maoDealer.add(Carta.getCarta(su,sy));
                                break;
                            }
                        case 1: // EAST
                            if(maoEsquerda.size() < 13) {
                                maoEsquerda.add(Carta.getCarta(su,sy));
                                break;
                            }
                        case 2:// SOUTHn
                            if(maoDireita.size() < 13) {
                                maoDireita.add(Carta.getCarta(su,sy));
                                break;
                            }
                        case 3:// WEST
                            if(maoParceiro.size() < 13) {
                                maoParceiro.add(Carta.getCarta(su,sy));
                                break;
                            } else {
                                continue loop; // volte para tentar dar a carta para os outros
                            }
                    }
                } while (false);
            }
        }
        
        // ordene em cada mão
        maoDealer.sort(new ComparaCartas());
        maoEsquerda.sort(new ComparaCartas());
        maoDireita.sort(new ComparaCartas());
        maoParceiro.sort(new ComparaCartas());
        	
        Naipe trunfo = maoDealer.get(  (int) (12 * (new Random()).nextDouble()) ).getNaipe();
        
        switch(dealer) {
            case NORTH: return new Bolsa(trunfo, maoDealer, maoEsquerda, maoParceiro, maoDireita, dealer);
            case WEST: return new Bolsa(trunfo, maoEsquerda, maoParceiro, maoDireita, maoDealer, dealer);
            case SOUTH: return new Bolsa(trunfo, maoParceiro, maoDireita, maoDealer, maoEsquerda, dealer);
            case EAST: return new Bolsa(trunfo, maoDireita, maoDealer, maoEsquerda, maoParceiro, dealer);
            default: return null;
        }
     
        
        
    }
    
}
