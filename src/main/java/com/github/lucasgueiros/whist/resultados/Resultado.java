/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.resultados;

import com.github.lucasgueiros.whist.jogador.Jogador;
import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.eventos.PartidaInterface;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.lucasgueiros.whist.usuario.Usuario;

/**
 *
 * @author lucas
 */
@Entity
@Table
public class Resultado {
    
    @ManyToOne
    private Usuario north;
    @ManyToOne
    private Usuario south;
    @ManyToOne
    private Usuario east;
    @ManyToOne
    private Usuario west;
    
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
    
    public Resultado(PartidaInterface partida) {
        this(partida.getDataDeInicio(),
                new Date(),partida.getPontosParaNS(),
                partida.getPontosParaEW());
        if(partida.getMesa().getJogador(Posicao.NORTH).isUsuario())
            this.north = (Usuario) partida.getMesa().getJogador(Posicao.NORTH);
        if(partida.getMesa().getJogador(Posicao.EAST).isUsuario())
            this.east = (Usuario) partida.getMesa().getJogador(Posicao.EAST);
        if(partida.getMesa().getJogador(Posicao.SOUTH).isUsuario())
            this.south = (Usuario) partida.getMesa().getJogador(Posicao.SOUTH);
        if(partida.getMesa().getJogador(Posicao.WEST).isUsuario())
            this.west = (Usuario) partida.getMesa().getJogador(Posicao.WEST);
    }
    
    public Resultado(Date start, Date end, int pointsNS, int pointsEW) {
        this.start = start;
        this.end = end;
        this.pointsNS = pointsNS;
        this.pointsEW = pointsEW;
    }

    public Usuario getNorth() {
        return north;
    }

    public void setNorth(Usuario north) {
        this.north = north;
    }

    public Usuario getSouth() {
        return south;
    }

    public void setSouth(Usuario south) {
        this.south = south;
    }

    public Usuario getEast() {
        return east;
    }

    public void setEast(Usuario east) {
        this.east = east;
    }

    public Usuario getWest() {
        return west;
    }

    public void setWest(Usuario west) {
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

    public boolean containsJogador(Usuario p) {
        return p != null  && (this.north.equals(p) || this.south.equals(p) || this.east.equals(p) || this.west.equals(p)) ;
    }

    public void setJogador(Posicao p, Usuario usuario) {
        switch(p) {
            case NORTH: north = usuario; break;
            case EAST: east = usuario; break;
            case SOUTH: south = usuario; break;
            case WEST: west = usuario; break;
        }
    }

    public Jogador getJogador(Posicao p) {
        switch(p) {
            case NORTH: return north;
            case EAST: return east;
            case SOUTH: return south;
            case WEST: return west;
        }
        return null;
    }
    
    
    
}
