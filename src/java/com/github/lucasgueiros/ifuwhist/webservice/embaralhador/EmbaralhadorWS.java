/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.webservice.embaralhador;

import com.github.lucasgueiros.ifuwhist.mesa.Posicao;
import com.github.lucasgueiros.ifuwhist.partida.bolsa.Bolsa;
import com.github.lucasgueiros.ifuwhist.partida.bolsa.EmbaralhadorSimples;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ogi
 */
@Path("/EmbaralhadorWS")
public class EmbaralhadorWS {
    
    @GET
    @Path("/simples")
    @Produces(MediaType.APPLICATION_XML)
    public Bolsa getBolsa(@QueryParam("dealer") Posicao dealer) {
        return new EmbaralhadorSimples().embaralhar(dealer);
    }
}
