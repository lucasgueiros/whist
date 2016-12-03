/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida.vaza;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas
 */
public class SimboloTest {
    
    @Test
    public void testToStringA() {
        assertEquals(Simbolo.A.toString(),"A");
    }
    @Test
    public void testToStringK() {
        assertEquals(Simbolo.K.toString(),"K");
    }
    @Test
    public void testToStringQ() {
        assertEquals(Simbolo.Q.toString(),"Q");
    }
    @Test
    public void testToStringJ() {
        assertEquals(Simbolo.J.toString(),"J");
    }
    
    @Test
    public void testToString10() {
        assertEquals(Simbolo.N10.toString(),"10");
    }
    
    @Test
    public void testToString2() {
        assertEquals(Simbolo.N2.toString(),"2");
    }
    
    @Test
    public void testToString3() {
        assertEquals(Simbolo.N3.toString(),"3");
    }
    
    @Test
    public void testToString4() {
        assertEquals(Simbolo.N4.toString(),"4");
    }
    
    @Test
    public void testToString5() {
        assertEquals(Simbolo.N5.toString(),"5");
    }
    
    @Test
    public void testToString6() {
        assertEquals(Simbolo.N6.toString(),"6");
    }
    
    @Test
    public void testToString7() {
        assertEquals(Simbolo.N7.toString(),"7");
    }
    
    @Test
    public void testToString8() {
        assertEquals(Simbolo.N8.toString(),"8");
    }
    
    @Test
    public void testToString9() {
        assertEquals(Simbolo.N9.toString(),"9");
    }
    
}
