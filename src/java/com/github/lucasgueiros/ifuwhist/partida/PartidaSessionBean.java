/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida;

import com.github.lucasgueiros.ifuwhist.jogador.JogadorFalso;
import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.github.lucasgueiros.ifuwhist.mesa.Mesa;
import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Carta;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Naipe;
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
    private Partida partida;
    private Jogador jogador;
    private Logger logger = LoggerFactory.getLogger(PartidaSessionBean.class);
    //private boolean pronto;
    
    // others beans
    //@ManagedProperty ("#{ctrl_autenticacao}")
    //private FacesContext facesContext = FacesContext.getCurrentInstance();
    //private JogadorSessionBean auth = (JogadorSessionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{contraladorAutenticacao}", JogadorSessionBean.class);
    //@ManagedProperty ("#{ctrl_mesas}")
    //private MesasApplicationBean mesas = (MesasApplicationBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{constroladorMesas}", MesasApplicationBean.class);
    
    // Repositório
    
    public PartidaSessionBean() {
    	
    }
    
    // Todas as informações do jogo
    // podem ser tiradas da Partida
    // Poucas podem ser feitas (jogar kkk)
    
    public int getStatus() {
        int status = 01;
        // TODO fazer direito
        return status;
    }
    
    public String getMyPosicao() {
        Posicao p = mesa.getPosicao(this.jogador);
        return p.toString();
    }
    
    public String getTrumphNaipe() {
        return partida.getTrumph().getNaipe().toString();
    }
    
    public String getTrumphNaipeValue() {
        return PropriedadesApplicationBean.getString("imagens.naipes."+getTrumphNaipe());
    }
    
    public String getTrumphCarta() {
        return partida.getTrumph().getSimbolo().toString();
    }
    
    public String getCurrentNaipe() {
        Naipe s = partida.getCurrentNaipe();
        if(s!=null) return s.toString();
        else return "NONE";
    }
    
    public int getPlacarTotal() {
        return partida.getNowTrickNumber();
    }
    
    public int getPlacarNS() {
        return partida.getTricksForNS();
    }
    
    public int getPlacarEW() {
        return partida.getTricksForEW();
    }
    
    public int getNumeroCartas(String position) {
        return partida.getNumberOfCartas(Posicao.valueOf(position));
    }
    
    public boolean isTurn(String position) {
        return partida.getTurn().equals(Posicao.valueOf(position));
    }
    
    public String getNome(String position) {
        Jogador pl = mesa.getJogador(Posicao.valueOf(position));
        return pl.getLogin();
    }
    
    public Carta getCarta(String position) {
        return partida.getPlayedCarta(Posicao.valueOf(position));
    }
    
    public String getCartaImagem(String position){
        Carta carta = partida.getPlayedCarta(Posicao.valueOf(position));
        String url = "cartas/";
        if(carta==null) return "";
        switch(carta.getSimbolo()) {
            case A: url += "ace"; break;
            case K: url += "king"; break;
            case Q: url += "queen"; break;
            case J: url += "jack"; break;            
            default: url+=carta.getSimbolo().toString();
        }
        url += "_of_";
        switch(carta.getNaipe()) {
            case CLUBS: url += "clubs"; break;
            case DIAMONDS: url += "diamonds"; break;
            case HEARTS: url += "hearts"; break;
            case SPADES: url += "spades"; break;
        }
        url += ".svg";
        return url;
    }
    
    public String goSozinho(Jogador north) {
        this.jogador = north;
        JogadorFalso south=new JogadorFalso(Posicao.SOUTH);
        JogadorFalso west=new JogadorFalso(Posicao.WEST);
        JogadorFalso east=new JogadorFalso(Posicao.EAST);
        mesa = new Mesa(north,south, east, west);
        partida = new Partida();
        partida.addListener(west);
        partida.addListener(east);
        partida.addListener(south);
        partida.setMesa(mesa);
        partida.setDealer(mesa.getProximoNowDealer());
        partida.deal();
        partida.start();
        
        return PropriedadesApplicationBean.getString("pagina.jogar");
    }
    
    public String go(Jogador jogador, Mesa mesa){//Partida partida) {
    	if(mesa == null) {
            //logger.error("mesa==null on if on PartidaSessionBean::go");
            //SaidaParaArquivo.file.println("mesa==null on if on PartidaSessionBean::go");
            //return PropriedadesApplicationBean.getString("pagina.deErro");
            return PropriedadesApplicationBean.getString("pagina.jogar.esperarParaSerIncluidoNumaMesa");
        }
            
    	
    	partida = new Partida();
        partida.setMesa(mesa);
        partida.setDealer(mesa.getProximoNowDealer());
        partida.deal();
        partida.start();
        
        //ctrl_fila.findMesa(ctrl_autenticacao.player) , ctrl_autenticacao.player
        this.jogador = jogador;
        //this.mesa = mesas.getMesa(jogador);
        this.mesa = mesa;
        return PropriedadesApplicationBean.getString("pagina.jogar");//"jogar.xhtml";
    }

    /*public MesasApplicationBean getMesas() {
        return mesas;
    }

    public void setMesas(MesasApplicationBean mesas) {
        this.mesas = mesas;
    }*/

    public boolean isPronto() {
        //this.mesa = mesas.getMesa(jogador);
        return mesa != null;
    }
    
    public boolean isPronto(Mesa mesa) {
        this.mesa = mesa;
        return this.mesa != null;
    }

    public List<Carta> getCartas() {
        Posicao posicao = this.mesa.getPosicao(this.jogador);
        return partida.getHand(posicao);
    }
    
    public /*String*/ void play(Carta c) {
        if(this.partida.getTurn().equals(this.mesa.getPosicao(this.jogador))) {
            try {
                this.partida.play(c);
            } catch (CartaNaoEstaNaMaoException ex) {  // isso só acontece se não for a vez da pessoa.
                logger.error("CartaNaoEstaNaMaoException");
                SaidaParaArquivo.file.println("CartaNaoEstaNaMaoException");
            } catch (CartaInvalidaException ex) { // isso acontece normalmente
                logger.error("CartaInvalidaException");
                SaidaParaArquivo.file.println("CartaInvalidaException");
            }
            
        }
        //return "#";
        
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
}
