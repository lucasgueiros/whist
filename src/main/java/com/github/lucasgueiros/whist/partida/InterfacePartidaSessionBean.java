/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.partida;

import com.github.lucasgueiros.whist.partida.eventos.PartidaInterface;
import com.github.lucasgueiros.whist.vaza.Carta;
import java.util.List;

/**
 *
 * @author lucas
 */
public interface InterfacePartidaSessionBean {
    
    public String getNaipeDeTrunfoImagem();
    public String getNaipeDetrunfoString();
    
    public String getPlacarString();
    public int getNumeroDeCartasParceiro();
    public int getNumeroDeCartasEsquerda();
    public int getNumeroDeCartasDireita();
    
    public String getCartaDaVazaStringParceiro();
    public String getCartaDaVazaStringProprio();
    public String getCartaDaVazaStringDireita();
    public String getCartaDaVazaStringEsquerda();
    
    public String getCartaDaVazaImagemParceiro();
    public String getCartaDaVazaImagemProprio();
    public String getCartaDaVazaImagemDireita();
    public String getCartaDaVazaImagemEsquerda();
    
    public List<Carta> getCartasProprias();
    
    public String getPosicaoComVezPropria();
    public String getPosicaoComVezParceiro();
    public String getPosicaoComVezDireita();
    public String getPosicaoComVezEsquerda();
    
    public String getVez();
    public boolean acabou();
    
    public void play(Carta c);
    
    public PartidaInterface getPartida();
    public void setPartida(Partida partida);
    /**
     * Enquanto você não mudar de vaza, vai ficar parada na mesma
     */
    public void irParaProximaVaza();
    
}
