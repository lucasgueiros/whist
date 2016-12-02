/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.ifuwhist.webservice.aletoriedade;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
//import com.sun.jersey.api.client.Client;
//import com.sun.jersey.api.client.WebResource;
//import com.sun.jersey.api.client.config.ClientConfig;
//import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



        
        
/**
 *
 * @author ogi
 */

public class GeradorAleatorioQrngAnu implements GeradorAleatorio {

    @Override
    public int[] get(int qtd) {
        int[] array = new int [qtd];

        try {
            String urlTxt = "https://qrng.anu.edu.au/API/jsonI.php?length="+qtd+"&type=uint8";
            URL url = new URL(urlTxt);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestMethod("POST");
            int responseCode = connection.getResponseCode();
            
            InputStream is = connection.getInputStream();
            Scanner scanner = new Scanner(is);
            String [] resultadoAsString = scanner.nextLine().split("\\[")[1].split("\\]")[0].split(",");
            for (int i = 0; i < qtd; i++) {
                array[i] = Integer.parseInt(resultadoAsString[i]);
            }
            connection.disconnect();            
        } catch (MalformedURLException ex) {
            Logger.getLogger(GeradorAleatorioQrngAnu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeradorAleatorioQrngAnu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return array;
    }

    
    public static void main(String[] args) {
        GeradorAleatorio gerador = new GeradorAleatorioQrngAnu();
        System.out.println("r=" + Arrays.toString(gerador.get(52,1,52,-1)));
    }

    @Override
    public int[] get(int n, int min, int max) {
        int [] numeros = get(n);
        int [] numerosAdpatados = new int [n];
        
        for (int i = 0; i < n; i++) {
            double numero = numeros[i];
            numero = numero / 255.0;
            numero = numero * max;
            numero = numero + min;
            numerosAdpatados[i] = (int) numero;
        }
        
        return numerosAdpatados;
    }
    
    @Override
    public int[] get(int n, int min, int max, int var) {
        int [] numeros = get(n);
        int [] numerosAdpatados = new int [n];
        
        for (int i = 0; i < n; i++) {
            double numero = numeros[i];
            numero = numero / 255.0;
            numero = numero * max;
            numero = numero + min;
            numerosAdpatados[i] = (int) numero;
            max = max + var;
        }
        
        return numerosAdpatados;
    }

}
