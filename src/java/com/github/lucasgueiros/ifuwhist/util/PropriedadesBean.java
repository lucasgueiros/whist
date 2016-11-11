/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.util;

import com.github.lucasgueiros.ifuwhist.util.propriedades.Propriedades;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author lucas
 */
@ApplicationScoped
public class PropriedadesBean {
    
    public Propriedades getPaginas() {
        return Propriedades.PAGINAS;
    }
    
    public Propriedades getMensagens() {
        return Propriedades.MENSAGENS;
    }
    
}
