/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.equipe;

/**
 *
 * @author lucas
 */
public enum TipoDeEquipe {

    /**
     *
     *//**
     *
     */
    INDIVIDUAL(1),DUPLA(2), QUADRA(4);

    TipoDeEquipe(int tamanho) {
        this.tamanho = tamanho;
    }
    
    private int tamanho;
    
    int getTamanho() {
        
        return tamanho;
    }
    
    
    
}
