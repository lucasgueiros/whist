/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.util.aletoriedade;

/**
 *
 * @author ogi
 */
public interface GeradorAleatorio {
    /**
     * Gera n números aleatórios entre 0, inclusive, e 255, inclusive.
     * @param n quantidade de números aleatórios.
     * @return 
     */
    public int [] get(int n);
    
    /**
     * Gera n números aleatórios entre min, inclusive, e max, inclusive.
     * @param n quantidade de números aleatórios.
     * @param min valor mínimo (inclusive)
     * @param max valor máximo (inclusive)
     * @return 
     */
    public int [] get(int n, int min, int max);
    
    /**
     * Gera n números aleatórios entre min, inclusive, e imax, inclusive,
     * indo imax varia no intervalo [max,max+(i*var)] e i varia de 0 até
     * n, exclusive.
     * @param n
     * @param min
     * @param max
     * @param var
     * @return 
     */
    public int [] get(int n, int min, int max, int var);
}
