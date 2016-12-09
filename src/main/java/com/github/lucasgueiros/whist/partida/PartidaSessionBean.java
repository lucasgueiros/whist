/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.partida;

import com.github.lucasgueiros.whist.partida.eventos.PartidaInterface;
import com.github.lucasgueiros.whist.partida.excecoes.NaoEstaNaVezException;
import com.github.lucasgueiros.whist.jogador.Jogador;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.github.lucasgueiros.whist.mesa.Mesa;
import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.vaza.Carta;
import com.github.lucasgueiros.whist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.whist.partida.excecoes.CartaNaoEstaNaMaoException;
import com.github.lucasgueiros.whist.util.SaidaParaArquivo;
import com.github.lucasgueiros.whist.util.propriedades.PropriedadesApplicationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lucas
 */
@SessionScoped
@ManagedBean
public class PartidaSessionBean implements /*PartidaListener,*/ Serializable, InterfacePartidaSessionBean {
    
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
    
    @Override
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

    @Override
    public PartidaInterface getPartida() {
        return partida;
    }

    @Override
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    @Override
    public String getNaipeDeTrunfoImagem() {
        return PropriedadesApplicationBean.getString("imagens.naipes."+getNaipeDetrunfoString());
    }

    @Override
    public String getNaipeDetrunfoString() {
        if(partida.getNaipeDeTrunfo()==null) return "";
        return partida.getNaipeDeTrunfo().toString();
    }

    @Override
    public String getPlacarString() {
        return partida.getVazasParaNS() + " NS x EW " + partida.getVazasParaEW();
    }

    @Override
    public int getNumeroDeCartasParceiro() {
        return partida.getNumeroDeCartas(mesa.getPosicao(jogador).getParceiro());
    }

    @Override
    public int getNumeroDeCartasEsquerda() {
        return partida.getNumeroDeCartas(mesa.getPosicao(jogador).getEsquerda());
    }

    @Override
    public int getNumeroDeCartasDireita() {
        return partida.getNumeroDeCartas(mesa.getPosicao(jogador).getDireita());
    }

    @Override
    public String getCartaDaVazaStringParceiro() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador).getParceiro());
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaStringProprio() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador));
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaStringDireita() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador).getDireita());
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaStringEsquerda() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador).getEsquerda());
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaImagemParceiro() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador).getParceiro());
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public String getCartaDaVazaImagemProprio() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador));
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public String getCartaDaVazaImagemDireita() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador).getDireita());
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public String getCartaDaVazaImagemEsquerda() {
        Carta carta = partida.getCartaDaVazaAtual(mesa.getPosicao(jogador).getEsquerda());
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public List<Carta> getCartasProprias() {
        Posicao posicao = this.mesa.getPosicao(this.jogador);
        return partida.getMao(posicao);
    }

    @Override
    public String getPosicaoPropria() {
        return this.mesa.getPosicao(this.jogador).toString();
    }

    @Override
    public String getPosicaoParceiro() {
        return this.mesa.getPosicao(this.jogador).getParceiro().toString();
    }

    @Override
    public String getPosicaoDireita() {
        return this.mesa.getPosicao(this.jogador).getDireita().toString();
    }

    @Override
    public String getPosicaoEsquerda() {
        return this.mesa.getPosicao(this.jogador).getEsquerda().toString();
    }

    @Override
    public String getVez() {
        return this.partida.getVez().toString();
    }
    
    
}
