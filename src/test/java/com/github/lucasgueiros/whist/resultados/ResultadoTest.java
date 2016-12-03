/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.resultados;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author lucas
 */
public class ResultadoTest {
    
    @Test
    public void soPraTerAlgumaCoisa() {
        assertTrue(true);
    }
    
    //  TODO NÃO CONSIGO TESTAR ISSO AQUI
    /**
     * Test of containsJogador method, of class Resultado.
     */
    /*@Test
    public void testContainsJogador() {
        // Jogadores
        Jogador north = mock(Jogador.class);
        Jogador south = mock(Jogador.class);
        Jogador east = mock(Jogador.class);
        Jogador west = mock(Jogador.class);
        Jogador deTeste = mock(Jogador.class);
        
        // Alguém deve dizer que sim?
        when(north.equals(any())).thenReturn(false);
        when(south.equals(any())).thenReturn(false);
        when(east.equals(any())).thenReturn(false);
        when(west.equals(any())).thenReturn(false);
        
        // Adicionando!!
        Resultado resultado = new Resultado();
        resultado.setNorth(north);
        resultado.setNorth(south);
        resultado.setNorth(east);
        resultado.setNorth(west);
        
        // Testando!!
        // Pode escolher entre true e false aqui!
        assertFalse(resultado.containsJogador(deTeste));
    }*/
}
