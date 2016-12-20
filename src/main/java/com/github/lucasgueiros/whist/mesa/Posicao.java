/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.mesa;

/**
 *
 * @author lucas
 */
public enum Posicao {
    NORTH, SOUTH, EAST, WEST; 
    
    public Posicao getParceiro() {
        switch(this) {
            case NORTH:
                return Posicao.SOUTH;
            case EAST:
                return Posicao.WEST;
            case SOUTH:
                return Posicao.NORTH;
            case WEST:
                return Posicao.EAST;
        }
        return null;
    }
    
    public Posicao getEsquerda() {
        switch(this) {
            case NORTH:
                return Posicao.EAST;
            case EAST:
                return Posicao.SOUTH;
            case SOUTH:
                return Posicao.WEST;
            case WEST:
                return Posicao.NORTH;
        }
        return null;
    }
    
    public Posicao getDireita() {
        switch(this) {
            case NORTH:
                return Posicao.WEST;
            case EAST:
                return Posicao.NORTH;
            case SOUTH:
                return Posicao.EAST;
            case WEST:
                return Posicao.SOUTH;
        }
        return null;
    }
    
    public Posicao next() {
        switch(this) {
            case NORTH:
                return Posicao.EAST;
            case EAST:
                return Posicao.SOUTH;
            case SOUTH:
                return Posicao.WEST;
            case WEST:
                return Posicao.NORTH;
        }
        return null;
    }

    @Override
    public String toString() {
        switch(this){
            case NORTH: return "Norte";
            case WEST: return "Oeste";
            case SOUTH: return "Sul";
            case EAST: return "Leste";
                            
        }
        return null;
    }
    
    
    
}
