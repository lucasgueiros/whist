/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.jogador;

/**
 *
 * @author ogi
 */
public interface Jogador {
    /**
     * Retorna uma String que identifica unicamente este jogador.
     * 
     * @return 
     */
    public String getLogin();
    /**
     * Diz se o jogador é um usuario.
     * 
     * @return 
     */
    public boolean isUsuario();
}
