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
public class GeradorPadrao {
    public static GeradorAleatorio getGerador(){
        GeradorAleatorio gerador = null;
        try {
            GeradorAleatorioQrngAnu geradoQrng = new GeradorAleatorioQrngAnu();
            geradoQrng.testar();
            gerador = geradoQrng;
        } catch (Exception ex) {
            gerador = new GeradorAleatorioJavaRandom();
        }
        return gerador;
    }
}
