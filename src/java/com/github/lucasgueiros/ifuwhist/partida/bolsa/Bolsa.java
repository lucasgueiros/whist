/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida.bolsa;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.vaza.Carta;
import com.github.lucasgueiros.ifuwhist.partida.vaza.Naipe;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * Define uma bolsa, i.e., um jogo que pode ser jogado várias vezes.
 * Ela define o naipe de trunfo, as cartas de cada jogador e o dealer,
 * o que consequentemente nos diz quem jogará a primeira carta.
 * 
 * @author lucas
 */
@XmlRootElement
public class Bolsa implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Naipe trunfo;
    private Collection<Carta> norte;
    private Collection<Carta> sul;
    private Collection<Carta> leste;
    private Collection<Carta> oeste;
    private Posicao dealer;

    public Bolsa() {
    }

    private Bolsa(Naipe trunfo, Posicao dealer) {
        this.trunfo = trunfo;
        this.dealer = dealer;
    }

    public Bolsa(Naipe trunfo, List<Carta> norte, List<Carta> leste, List<Carta> sul, List<Carta> oeste, Posicao dealer) {
        this(trunfo, dealer);
        this.norte = norte;
        this.sul = sul;
        this.leste = leste;
        this.oeste = oeste;
    }
    
    public Bolsa(Naipe trunfo, Carta[] norte, Carta[] leste, Carta[] sul, Carta[] oeste, Posicao dealer) {
        this(trunfo, dealer);
        this.norte = new LinkedList<>();
        this.leste = new LinkedList<>();
        this.oeste = new LinkedList<>();
        this.sul = new LinkedList<>();
        for (int i = 0; i < 13; i++) {
            this.norte.add(norte[i]);
            this.leste.add(leste[i]);
            this.sul.add(sul[i]);
            this.oeste.add(oeste[i]);
        }
    }

    public Naipe getTrunfo() {
        return trunfo;
    }

    @XmlElement
    public void setTrunfo(Naipe trunfo) {
        this.trunfo = trunfo;
    }

    public Posicao getDealer() {
        return dealer;
    }
    
    @XmlElement
    public void setDealer(Posicao dealer) {
        this.dealer = dealer;
    }
    
    public Collection<Carta> getMao(Posicao posicao) {
        switch(posicao) {
            case EAST: return leste;
            case NORTH: return norte;
            case SOUTH: return sul;
            case WEST: return oeste;
            default: return null;
        }
    }

    public Collection<Carta> getNorte() {
        return norte;
    }

    @XmlElement (name="NORTH")
    public void setNorte(Collection<Carta> norte) {
        this.norte = norte;
    }

    public Collection<Carta> getSul() {
        return sul;
    }

    @XmlElement (name="SOUTH")
    public void setSul(Collection<Carta> sul) {
        this.sul = sul;
    }

    public Collection<Carta> getLeste() {
        return leste;
    }

    @XmlElement (name="EAST")
    public void setLeste(Collection<Carta> leste) {
        this.leste = leste;
    }

    public Collection<Carta> getOeste() {
        return oeste;
    }

    @XmlElement (name="WEST")
    public void setOeste(Collection<Carta> oeste) {
        this.oeste = oeste;
    }
    
    
    
}
