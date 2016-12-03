/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.vaza;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author lucas
 */
public class NaipeTest {
   
    /**
     * Test of toString method, of class Naipe.
     */
    @Test
    public void testToString1() {
        assertEquals(Naipe.CLUBS.toString(),"C");
    }
    
    /**
     * Test of toString method, of class Naipe.
     */
    @Test
    public void testToString2() {
        assertEquals(Naipe.DIAMONDS.toString(),"D");
    }
    
    /**
     * Test of toString method, of class Naipe.
     */
    @Test
    public void testToString3() {
        assertEquals(Naipe.HEARTS.toString(),"H");
    }
    
    /**
     * Test of toString method, of class Naipe.
     */
    @Test
    public void testToString4() {
        assertEquals(Naipe.SPADES.toString(),"S");
    }
    
}
