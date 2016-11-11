/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.resultados;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.lucasgueiros.ifuwhist.jogador.Jogador;

/**
 *
 * @author lucas
 */
@Entity
@Table
public class Resultado {
    
    @ManyToOne
    private Jogador north;
    @ManyToOne
    private Jogador south;
    @ManyToOne
    private Jogador east;
    @ManyToOne
    private Jogador west;
    
    @Id
    @GeneratedValue
    @Column
    private long id;
    @Column (name="inicio")
    private Date start;
    @Column (name="fim")
    private Date end;
    
    @Column
    private int pointsNS;
    @Column
    private int pointsEW;    

    public Resultado() {
    }

    public Resultado(Date start, Date end, int pointsNS, int pointsEW) {
        this.start = start;
        this.end = end;
        this.pointsNS = pointsNS;
        this.pointsEW = pointsEW;
    }

    public Jogador getNorth() {
        return north;
    }

    public void setNorth(Jogador north) {
        this.north = north;
    }

    public Jogador getSouth() {
        return south;
    }

    public void setSouth(Jogador south) {
        this.south = south;
    }

    public Jogador getEast() {
        return east;
    }

    public void setEast(Jogador east) {
        this.east = east;
    }

    public Jogador getWest() {
        return west;
    }

    public void setWest(Jogador west) {
        this.west = west;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public int getPointsNS() {
        return pointsNS;
    }

    public void setPointsNS(int pointsNS) {
        this.pointsNS = pointsNS;
    }

    public int getPointsEW() {
        return pointsEW;
    }

    public void setPointsEW(int pointsEW) {
        this.pointsEW = pointsEW;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void alterar(Resultado t) {
        this.east = t.getEast();
        this.west = t.getWest();
        this.north = t.getNorth();
        this.south = t.getSouth();
        this.end = t.getEnd();
        this.start = t.getStart();
        this.pointsNS = t.getPointsNS();
        this.pointsEW = t.getPointsEW();
    }

    public boolean containsJogador(Jogador p) {
        return p != null  && (this.north.equals(p) || this.south.equals(p) || this.east.equals(p) || this.west.equals(p)) ;
    }
    
    
    
}
