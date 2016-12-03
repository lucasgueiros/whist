/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida.bolsa;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.vaza.Carta;
import com.github.lucasgueiros.ifuwhist.webservice.aletoriedade.GeradorAleatorio;

/**
 *
 * @author lucas
 */
public interface Embaralhador {
    /**
     * 
     * Gera um nova bolsa aleatoriamente, apenas define-se o "imaginável" dealer.
     * 
     * @param posicao dealer
     * @param geradorAleatorio
     * @return 
     */
    public Bolsa embaralhar(Posicao posicao, GeradorAleatorio geradorAleatorio);
}
