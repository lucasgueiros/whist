/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.mesa;

/**
 *
 * @author lucas
 */
public enum Posicao {
    NORTH, SOUTH, EAST, WEST; 
    
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
            case NORTH: return "NORTH";
            case WEST: return "WEST";
            case SOUTH: return "SOUTH";
            case EAST: return "EAST";
                            
        }
        return null;
    }
    
    
    
}
