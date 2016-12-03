/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida;

import com.github.lucasgueiros.ifuwhist.partida.excecoes.NaoEstaNaVezException;
import com.github.lucasgueiros.ifuwhist.jogador.JogadorFalso;
import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.github.lucasgueiros.ifuwhist.mesa.Mesa;
import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.vaza.Carta;
import com.github.lucasgueiros.ifuwhist.partida.vaza.Naipe;
import com.github.lucasgueiros.ifuwhist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.ifuwhist.partida.excecoes.CartaNaoEstaNaMaoException;
import com.github.lucasgueiros.ifuwhist.util.SaidaParaArquivo;
import com.github.lucasgueiros.ifuwhist.util.propriedades.PropriedadesApplicationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lucas
 */
@SessionScoped
@ManagedBean
public class PartidaSessionBean implements /*PartidaListener,*/ Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mesa mesa;
    private PartidaInterface partida;
    private Jogador jogador;
    private Logger logger = LoggerFactory.getLogger(PartidaSessionBean.class);
    
    public PartidaSessionBean() {
    	
    }
    
    
    public String getMyPosicao() {
        Posicao p = mesa.getPosicao(this.jogador);
        return p.toString();
    }
    
    public String getTrumphNaipe() {
        return partida.getNaipeDeTrunfo().toString();//partida.getTrumph().getNaipe().toString();
    }
    
    public String getTrumphNaipeValue() {
        return PropriedadesApplicationBean.getString("imagens.naipes."+getTrumphNaipe());
    }
    
    public String getCurrentNaipe() {
        Naipe s = partida.getNaipeCorrente();
        if(s!=null) return s.toString();
        else return "NONE";
    }
    
    public int getPlacarTotal() {
        return partida.getNumeroDaVaza();
    }
    
    public int getPlacarNS() {
        return partida.getVazasParaNS();
    }
    
    public int getPlacarEW() {
        return partida.getVazasParaEW();
    }
    
    public int getNumeroCartas(String position) {
        return partida.getNumeroDeCartas(Posicao.valueOf(position));
    }
    
    public boolean isTurn(String position) {
        return partida.estaNaVezDe(Posicao.valueOf(position));
    }
    
    public String getNome(String position) {
        Jogador pl = mesa.getJogador(Posicao.valueOf(position));
        return pl.getLogin();
    }
    
    public String getCarta(String position) {
        Carta carta = partida.getCartaDaVazaAtual(Posicao.valueOf(position));
        if(carta==null) return "";
        return carta.toString();
    }
    
    public String getCartaImagem(String position){
        Carta carta = partida.getCartaDaVazaAtual(Posicao.valueOf(position));
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta);
    }
    
    public String goSozinho(Jogador north) {
        this.jogador = north;
        JogadorFalso south=new JogadorFalso(Posicao.SOUTH);
        JogadorFalso west=new JogadorFalso(Posicao.WEST);
        JogadorFalso east=new JogadorFalso(Posicao.EAST);
        mesa = new Mesa(north,south, east, west);
        partida = new Partida(mesa,mesa.getProximoNowDealer());
        partida.addListener(west);
        partida.addListener(east);
        partida.addListener(south);
        partida.iniciar();
        return PropriedadesApplicationBean.getString("pagina.jogar");
    }
    
    public String go(Jogador jogador, Mesa mesa){//Partida partida) {
    	if(mesa == null) {
            return PropriedadesApplicationBean.getString("pagina.jogar.esperarParaSerIncluidoNumaMesa");
        }
            
    	
    	partida = new Partida(mesa,mesa.getProximoNowDealer());
        partida.iniciar();
        this.jogador = jogador;
        this.mesa = mesa;
        return PropriedadesApplicationBean.getString("pagina.jogar");//"jogar.xhtml";
    }

    public boolean isPronto() {
        return mesa != null;
    }
    
    public boolean isPronto(Mesa mesa) {
        this.mesa = mesa;
        return this.mesa != null;
    }

    public List<Carta> getCartas() {
        Posicao posicao = this.mesa.getPosicao(this.jogador);
        return partida.getMao(posicao);
    }
    
    public void play(Carta c) {
            try {
                partida.jogar(this.mesa.getPosicao(this.jogador),c);
            } catch (CartaNaoEstaNaMaoException ex) {  // isso só acontece se não for a vez da pessoa.
                logger.error("CartaNaoEstaNaMaoException");
                SaidaParaArquivo.file.println("CartaNaoEstaNaMaoException");
            } catch (CartaInvalidaException ex) { // isso acontece normalmente
                logger.error("CartaInvalidaException");
                SaidaParaArquivo.file.println("CartaInvalidaException");
            } catch (NaoEstaNaVezException ex) {
                logger.error("NaoEstaNaVezException");
                SaidaParaArquivo.file.println("NaoEstaNaVezException");
            }
    }

    public PartidaInterface getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
}
