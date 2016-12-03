/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.usuario;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lucas
 */
public class UsuarioTest {
    
    @Test
    public void testPassword1() {
        String senhaCerta = "oi";
        String senhaTeste = "oi";
        
        // preparando
        Usuario j = new Usuario();
        j.setSenha(senhaCerta);
        
        // teste
        assertTrue(j.autenticar(senhaTeste));
    }
    
    @Test
    public void testPassword2() {
        String senhaCerta = "oi";
        String senhaTeste = "oib";
        
        // preparando
        Usuario j = new Usuario();
        j.setSenha(senhaCerta);
        
        // teste
        assertFalse(j.autenticar(senhaTeste));
    }
    
    @Test
    public void testPassword3() {
        String senhaCerta = "oi";
        String senhaTeste = "";
        
        // preparando
        Usuario j = new Usuario();
        j.setSenha(senhaCerta);
        
        // teste
        assertFalse(j.autenticar(senhaTeste));
    }
    
    @Test
    public void testPassword4() {
        String senhaCerta = "oi";
        String senhaTeste = null;
        
        // preparando
        Usuario j = new Usuario();
        j.setSenha(senhaCerta);
        
        // teste
        assertFalse(j.autenticar(senhaTeste));
    }
    
    /*@Test
    public void testAlterar1() {
        Usuario jogador1 = new Usuario("nome", "login", "senha");
        Usuario jogador2 = new Usuario("nome", "login", "senha");
        
        // alterar
        jogador1.alterar(jogador2);
        
        // testando!!
        assertEquals(jogador2.getNome(),jogador1.getNome());
        assertEquals(jogador2.getLogin(),jogador1.getLogin());
        assertEquals(jogador2.getSenha(),jogador1.getSenha());
    }
    
    @Test
    public void testAlterar2() {
        Usuario jogador1 = new Usuario("nome", "login", "senha");
        Usuario jogador2 = new Usuario("novo nome", "login", "senha");
        
        // alterar
        jogador1.alterar(jogador2);
        
        // testando!!
        assertEquals(jogador2.getNome(),jogador1.getNome());
        assertEquals(jogador2.getLogin(),jogador1.getLogin());
        assertEquals(jogador2.getSenha(),jogador1.getSenha());
    }
    
    @Test
    public void testAlterar3() {
        Usuario jogador1 = new Usuario("nome", "login", "senha");
        Usuario jogador2 = new Usuario("nome", "novo login", "senha");
        
        // alterar
        jogador1.alterar(jogador2);
        
        // testando!!
        assertEquals(jogador2.getNome(),jogador1.getNome());
        assertEquals(jogador2.getLogin(),jogador1.getLogin());
        assertEquals(jogador2.getSenha(),jogador1.getSenha());
    }
    
    @Test
    public void testAlterar4() {
        Usuario jogador1 = new Usuario("nome", "login", "senha");
        Usuario jogador2 = new Usuario("nome", "login", "nova senha");
        
        // alterar
        jogador1.alterar(jogador2);
        
        // testando!!
        assertEquals(jogador2.getNome(),jogador1.getNome());
        assertEquals(jogador2.getLogin(),jogador1.getLogin());
        assertEquals(jogador2.getSenha(),jogador1.getSenha());
    }
    
    @Test
    public void testAlterar5() {
        Usuario jogador1 = new Usuario("nome", "login", "senha");
        Usuario jogador2 = new Usuario("nome", "login", null);
        
        // alterar
        jogador1.alterar(jogador2);
        
        // testando!!
        assertEquals(jogador2.getNome(),jogador1.getNome());
        assertEquals(jogador2.getLogin(),jogador1.getLogin());
        assertEquals(jogador2.getSenha(),jogador1.getSenha());
    }
    
    @Test
    public void testAlterar6() {
        Usuario jogador1 = new Usuario("nome", "login", "senha");
        Usuario jogador2 = new Usuario("nome", null, "senha");
        
        // alterar
        jogador1.alterar(jogador2);
        
        // testando!!
        assertEquals(jogador2.getNome(),jogador1.getNome());
        assertEquals(jogador2.getLogin(),jogador1.getLogin());
        assertEquals(jogador2.getSenha(),jogador1.getSenha());
    }
    
    @Test
    public void testAlterar7() {
        Usuario jogador1 = new Usuario("nome", "login", "senha");
        Usuario jogador2 = new Usuario(null, "login", "senha");
        
        // alterar
        jogador1.alterar(jogador2);
        
        // testando!!
        assertEquals(jogador2.getNome(),jogador1.getNome());
        assertEquals(jogador2.getLogin(),jogador1.getLogin());
        assertEquals(jogador2.getSenha(),jogador1.getSenha());
    }*/
    
}
