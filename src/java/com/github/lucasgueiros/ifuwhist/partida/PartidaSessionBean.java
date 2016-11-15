/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.partida;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.github.lucasgueiros.ifuwhist.jogador.JogadorSessionBean;
import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import com.github.lucasgueiros.ifuwhist.mesa.MesasApplicationBean;
import com.github.lucasgueiros.ifuwhist.mesa.Mesa;
import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Carta;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Naipe;
import com.github.lucasgueiros.ifuwhist.partida.excecoes.CartaInvalidaException;
import com.github.lucasgueiros.ifuwhist.partida.excecoes.CartaNaoEstaNaMaoException;
import com.github.lucasgueiros.ifuwhist.resultados.Resultado;
import com.github.lucasgueiros.ifuwhist.util.propriedades.PropriedadesApplicationBean;
import com.github.lucasgueiros.ifuwhist.util.repositorio.Repositorio;
import com.github.lucasgueiros.ifuwhist.util.repositorio.RepositorioJPA;

/**
 *
 * @author lucas
 */
@SessionScoped
@ManagedBean (name="controladorPartida")
public class PartidaSessionBean implements /*PartidaListener,*/ Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Mesa mesa;
    private Partida partida;
    private Jogador jogador;
    //private boolean pronto;
    
    // others beans
    //@ManagedProperty ("#{ctrl_autenticacao}")
    FacesContext faces = FacesContext.getCurrentInstance();
    private JogadorSessionBean auth = (JogadorSessionBean) faces.getApplication().evaluateExpressionGet(faces, "#{contraladorAutenticacao}", JogadorSessionBean.class);
    //@ManagedProperty ("#{ctrl_mesas}")
    private MesasApplicationBean mesas = (MesasApplicationBean) faces.getApplication().evaluateExpressionGet(faces, "#{constroladorMesas}", MesasApplicationBean.class);
    
    // Repositório
    private Repositorio<Resultado> repositorioResultado;
    
    public PartidaSessionBean() {
    	repositorioResultado = new RepositorioJPA<>(Resultado.class);
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
        return pl.getNome();
    }
    
    public Carta getCarta(String position) {
        return partida.getPlayedCarta(Posicao.valueOf(position));
    }
    
    public String go(Mesa mesa){//Partida partida) {
    	if(mesa == null) return PropriedadesApplicationBean.getString("pagina.deErro");
    	
    	Partida partida = new Partida();
        partida.setMesa(mesa);
        partida.setDealer(mesa.getProximoNowDealer());
        partida.deal();
        partida.start();
        
        //ctrl_fila.findMesa(ctrl_autenticacao.player) , ctrl_autenticacao.player
        this.jogador = auth.getJogador();
        this.mesa = mesas.getMesa(jogador);
        if(this.mesa == null) {
            return PropriedadesApplicationBean.getString("pagina.esperarPorMesa");//"espera.xhtml";
        } else {
            //rm = t.getRunning();
        	this.partida = partida;
            return PropriedadesApplicationBean.getString("pagina.jogar");//"jogar.xhtml";
        }
    }

    public JogadorSessionBean getAuth() {
        return auth;
    }

    public void setAuth(JogadorSessionBean auth) {
        this.auth = auth;
    }

    public MesasApplicationBean getMesas() {
        return mesas;
    }

    public void setMesas(MesasApplicationBean mesas) {
        this.mesas = mesas;
    }

    public boolean isPronto() {
        this.mesa = mesas.getMesa(jogador);
        return mesa != null;
    }

    public List<Carta> getCartas() {
        return partida.getHand(this.mesa.getPosicao(this.jogador));
    }
    
    public /*String*/ void play(Carta c) {
        if(this.partida.getTurn().equals(this.mesa.getPosicao(this.jogador))) {
            try {
                this.partida.play(c);
            } catch (CartaNaoEstaNaMaoException ex) {  // isso só acontece se não for a vez da pessoa.
                Logger.getLogger(PartidaSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CartaInvalidaException ex) { // isso acontece normalmente
                Logger.getLogger(PartidaSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(partida.getPartidaTerminada() != null ) {
                //try {
                    //RepositoryFactory.getRepositorioPartidaTerminada().adicionar(rm.getPartidaTerminada());
                this.repositorioResultado.adicionar(partida.getPartidaTerminada());
                //} catch (IdNaoDisponivelException ex) {
                //    Logger.getLogger(ControladorPartida.class.getName()).log(Level.SEVERE, null, ex);
                //}
            }
        }
        //return "#";
        
    }
    
    public Resultado getPartida () {
        return partida.getPartidaTerminada();
    }
}
