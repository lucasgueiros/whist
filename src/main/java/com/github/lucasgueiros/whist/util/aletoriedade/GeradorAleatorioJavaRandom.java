/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.util.aletoriedade;

/**
 *
 * @author lucas
 */
public class GeradorAleatorioJavaRandom implements GeradorAleatorio {

    @Override
    public int[] get(int n) {
       int [] numeros = new int [n];
        for (int i = 0; i < n; i++) {
            numeros[i] = (int) Math.random();
        }
        return numeros;
    }

    @Override
    public int[] get(int n, int min, int max) {
        int [] numeros = get(n);
        int [] numerosAdpatados = new int [n];
        
        for (int i = 0; i < n; i++) {
            double numero = numeros[i];
            numero = numero / 255.0;
            numero = numero * max;
            numero = numero + min;
            numerosAdpatados[i] = (int) numero;
        }
        
        return numerosAdpatados;
    }
    
    @Override
    public int[] get(int n, int min, int max, int var) {
        int [] numeros = get(n);
        int [] numerosAdpatados = new int [n];
        
        for (int i = 0; i < n; i++) {
            double numero = numeros[i];
            numero = numero / 255.0;
            numero = numero * max;
            numero = numero + min;
            numerosAdpatados[i] = (int) numero;
            max = max + var;
        }
        
        return numerosAdpatados;
    }
    
}
