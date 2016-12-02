/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida.bolsa;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.vaza.Carta;
import com.github.lucasgueiros.ifuwhist.partida.vaza.Naipe;
import com.github.lucasgueiros.ifuwhist.webservice.aletoriedade.GeradorAleatorio;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class EmbaralhadorV3 implements Embaralhador {

    @Override
    public Bolsa embaralhar(Posicao posicao, GeradorAleatorio geradorAleatorio) {
        List<Carta> norte = new LinkedList<>();
        List<Carta> leste = new LinkedList<>();
        List<Carta> sul = new LinkedList<>();
        List<Carta> oeste = new LinkedList<>();
        
        
        
        Bolsa bolsa = new Bolsa(Naipe.CLUBS, norte, leste, sul, oeste, posicao);
        return bolsa;
    }
    
}
