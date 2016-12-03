/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.bolsa;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.vaza.Carta;
import com.github.lucasgueiros.whist.webservice.aletoriedade.GeradorAleatorio;

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
