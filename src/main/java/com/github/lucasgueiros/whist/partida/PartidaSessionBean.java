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

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.vaza.Carta;
import com.github.lucasgueiros.whist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.whist.util.SaidaParaArquivo;
import com.github.lucasgueiros.whist.util.propriedades.PropriedadesApplicationBean;
import java.util.logging.Level;
import org.primefaces.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.omnifaces.util.Ajax;

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
    private PartidaInterface partida;
    //private Jogador jogador;
    private Logger logger = LoggerFactory.getLogger(PartidaSessionBean.class);
    private Posicao posicao;
    
    private int vaza = 0;
    
    public PartidaSessionBean() {
    	
    }
    
    public String goSozinho(Jogador north) {
        //this.jogador = north;
        JogadorFalso south=new JogadorFalso(Posicao.SOUTH);
        JogadorFalso west=new JogadorFalso(Posicao.WEST);
        JogadorFalso east=new JogadorFalso(Posicao.EAST);
        //mesa = new Mesa(north,south, east, west);
        this.posicao = Posicao.NORTH;
        partida = new Partida(Posicao.NORTH);
        partida.addListener(west);
        partida.addListener(east);
        partida.addListener(south);
        for(Posicao p : Posicao.values()){
            partida.estaPronto(posicao);
        }
        return PropriedadesApplicationBean.getString("pagina.jogar");
    }
    
    public String go(){//Partida partida) {
    	//if(mesa == null) {
        //    return PropriedadesApplicationBean.getString("pagina.jogar.esperarParaSerIncluidoNumaMesa");
        //}
            
    	
    	//partida = new Partida(mesa,mesa.getProximoNowDealer());
        //partida.iniciar();
        partida.estaPronto(posicao);
        //this.jogador = jogador;
        //this.mesa = mesa;
        return PropriedadesApplicationBean.getString("pagina.jogar");//"jogar.xhtml";
    }

    public boolean isPronto() {
        //return mesa != null; 
        return this.partida.iniciou();
    }
    
    /*public boolean isPronto(Mesa mesa) {
        this.mesa = mesa;
        return this.mesa != null;
    }*/
    
    @Override
    public void play(Carta c) {
            try {
                partida.jogar(posicao,c);
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
        return partida.getVazasParaNS(vaza) + " NS x EW " + partida.getVazasParaEW(vaza);
    }

    @Override
    public int getNumeroDeCartasParceiro() {
        return partida.getNumeroDeCartas(vaza,posicao.getParceiro());
    }

    @Override
    public int getNumeroDeCartasEsquerda() {
        return partida.getNumeroDeCartas(vaza,posicao.getEsquerda());
    }

    @Override
    public int getNumeroDeCartasDireita() {
        return partida.getNumeroDeCartas(vaza,posicao.getDireita());
    }

    @Override
    public String getCartaDaVazaStringParceiro() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao.getParceiro());
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaStringProprio() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao);
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaStringDireita() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao.getDireita());
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaStringEsquerda() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao.getEsquerda());
        if(carta==null) return "";
        return carta.toString();
    }

    @Override
    public String getCartaDaVazaImagemParceiro() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao.getParceiro());
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public String getCartaDaVazaImagemProprio() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao);
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public String getCartaDaVazaImagemDireita() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao.getDireita());
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public String getCartaDaVazaImagemEsquerda() {
        Carta carta = partida.getCartaDaVaza(vaza,posicao.getEsquerda());
        if(carta==null) return "";
        return PropriedadesApplicationBean.getImagem(carta.getNaipe().toStringExt(),carta.getSimbolo().toStringExt());
    }

    @Override
    public List<Carta> getCartasProprias() {
        return partida.getMao(posicao);
    }

    @Override
    public String getPosicaoComVezPropria() {
        if(posicao.equals(partida.getVez(vaza))){
            return ">" +this.posicao.toString()+"<";
        } else {
            return this.posicao.toString();
        }
    }

    @Override
    public String getPosicaoComVezParceiro() {
        if(posicao.getParceiro().equals(partida.getVez(vaza))){
            return ">" +this.posicao.getParceiro().toString()+"<";
        } else {
            return this.posicao.getParceiro().toString();
        }
    }

    @Override
    public String getPosicaoComVezDireita() {
        if(posicao.getDireita().equals(partida.getVez(vaza))){
            return ">" +this.posicao.getDireita().toString()+"<";
        } else {
            return this.posicao.getDireita().toString();
        }
    }

    @Override
    public String getPosicaoComVezEsquerda() {
        if(posicao.getEsquerda().equals(partida.getVez(vaza))){
            return ">" +this.posicao.getEsquerda().toString()+"<";
        } else {
            return this.posicao.getEsquerda().toString();
        }
    }

    @Override
    public String getVez() {
        if(partida.getVez(vaza)==null) return "";
        return this.partida.getVez(vaza).toString();
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }

    @Override
    public boolean acabou() {
        return this.partida.acabou();
    }
    
    private boolean mudou = false;

    public boolean isMudou() {
        return mudou;
    }

    public void setMudou(boolean mudou) {
        this.mudou = mudou;
    }
    
    @Override
    public synchronized void irParaProximaVaza() {
            try {
                wait(1000);
                vaza = partida.getNumeroDaVaza() - 1;
                mudou = true;
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(PartidaSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
