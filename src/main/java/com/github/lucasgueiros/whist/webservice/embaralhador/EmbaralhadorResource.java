/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.webservice.embaralhador;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.partida.bolsa.Bolsa;
import com.github.lucasgueiros.whist.partida.bolsa.EmbaralhadorSimples;
import com.github.lucasgueiros.whist.webservice.aletoriedade.GeradorAleatorioJavaRandom;
import com.github.lucasgueiros.whist.webservice.aletoriedade.GeradorAleatorioQrngAnu;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ogi
 */
@Path("/qrng")
public class EmbaralhadorResource {
    
    /*@GET
    @Path("/java")
    @Produces(MediaType.APPLICATION_JSON)
    public Bolsa getBolsaSimplesJava(@QueryParam("dealer") Posicao dealer) {
        return new EmbaralhadorSimples().embaralhar(dealer, new GeradorAleatorioJavaRandom());
    }*/
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Bolsa getBolsaQrng(@QueryParam("dealer") Posicao dealer) {
        return new EmbaralhadorSimples().embaralhar(dealer, new GeradorAleatorioQrngAnu());
    }
}
