/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.partida;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Carta;
import com.github.lucasgueiros.ifuwhist.partida.cartas.Naipe;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * Vaza não considera se a carta está na mão ou não!!
 *
 * @author lucas
 */
public class Vaza {

    private Posicao primeiro;
    private Naipe trunfo;
    private Map<Posicao, Carta> vaza;
    private Posicao ganhador;
    private boolean acabou;
    private Posicao vez;

    public Vaza(Posicao primeiro, Naipe trunfo) {
        this.primeiro = primeiro;
        this.trunfo = trunfo;
        this.vaza = new EnumMap<>(Posicao.class);
        this.acabou = false;
        this.vez = primeiro;
    }

    public void jogar(Carta carta) throws CartaJaJogadaException {
        if(vaza.values().contains(carta))
            throw new CartaJaJogadaException();
        // coloque a carta na trick
        this.vaza.put(vez, carta);
        // mova a vez para o próximo jogador
        this.vez = this.vez.next();
        // se for a última carta, verifique o vencedor e termine a vaza
        if (vez.equals(primeiro)) {
            Naipe corrente = vaza.get(primeiro).getNaipe();
            ganhador = primeiro;
            for (Posicao p : Posicao.values()) {
                Carta considerada = vaza.get(p);
                Carta vencedora = vaza.get(ganhador);

                if (vencedora.getNaipe().equals(trunfo)
                        && considerada.getNaipe().equals(trunfo)
                        && considerada.getSimbolo().compareTo(vencedora.getSimbolo()) < 0) {
                    ganhador = p;
                    // caso o vencedor tenha usando corrente
                    // e eu tenha tentado corrente também
                    // e a minha carta seja maior que a deçe
                } else if (vencedora.getNaipe().equals(corrente)
                        && considerada.getNaipe().equals(corrente)
                        && considerada.getSimbolo().compareTo(vencedora.getSimbolo()) < 0) {
                    ganhador = p;
                    // caso o vencedor tenha usando corrente
                    // e eu tenha trunfo!
                } else if (!corrente.equals(trunfo)
                        && vencedora.getNaipe().equals(corrente)
                        && considerada.getNaipe().equals(trunfo)) {
                    ganhador = p;
                }
            }
            acabou = true;
            vez=null;
        }
    }

    public Posicao getPrimeiro() {
        return primeiro;
    }

    public Naipe getTrunfo() {
        return trunfo;
    }

    public Posicao getGanhador() {
        return ganhador;
    }

    public boolean isAcabou() {
        return acabou;
    }

    public Posicao getVez() {
        return vez;
    }
    
    public Naipe getCorrente() {
        if(vaza.get(primeiro)==null)
            return null;
        else
            return vaza.get(primeiro).getNaipe();
    }
 
    public Map<Posicao,Carta> getCartas() {
        Map<Posicao,Carta> toReturn = new EnumMap<Posicao,Carta>(Posicao.class);
        for (Map.Entry<Posicao, Carta> entry : vaza.entrySet()) {
                toReturn.put(entry.getKey(), entry.getValue());
            }
        return toReturn;
    }
    
}
