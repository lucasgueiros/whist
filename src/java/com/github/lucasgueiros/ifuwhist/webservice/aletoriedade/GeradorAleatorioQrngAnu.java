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
        int[] array = {2, 2};

        try {
            /*ClientConfig config = new DefaultClientConfig();
            Client client = Client.create(config);
            WebResource service = client.resource(UriBuilder.fromUri("https://qrng.anu.edu.au/").build());
            service=service.path("API/").path("jsonI.php").queryParam("length","10").queryParam("type","uint8");
            // getting JSON data
            System.out.println(service.accept(MediaType.APPLICATION_XML).get(String.class));/*/
            
            //String urlTxt = "https://172.28.2.2:3128/API/jsonI.php?length="+qtd+"&type=uint8";
            String urlTxt = "https://qrng.anu.edu.au/API/jsonI.php?length="+qtd+"&type=uint8";
            System.out.println(urlTxt);
            URL url = new URL(urlTxt);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestMethod("POST");
            int responseCode = connection.getResponseCode();
            
            InputStream is = connection.getInputStream();
            is.read();
            Scanner scanner = new Scanner(is);
            //System.out.println(connection.getContent());
            System.out.println(scanner.nextLine());
            System.out.println(responseCode);
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
        System.out.println("r=" + Arrays.toString(gerador.get(2)));
    }

}
