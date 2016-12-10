/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.lucasgueiros.whist.webservice.usandows;

import com.github.lucasgueiros.whist.mesa.Posicao;
import com.github.lucasgueiros.whist.bolsa.Bolsa;
import com.github.lucasgueiros.whist.vaza.Carta;
import com.github.lucasgueiros.whist.vaza.Naipe;
import com.github.lucasgueiros.whist.util.aletoriedade.GeradorAleatorioQrngAnu;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author lucas
 */
public class UsandoWS {
    
    public static void main(String [] args) {
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Quem é o dador?");
            String dador = scan.next().toUpperCase();
            
            String urlTxt = "http://localhost:8080/whist/embaralhar/qrng?dealer=" + dador;
            URL url = new URL(urlTxt);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestProperty("Accept-Charset", charset);
            connection.setRequestMethod("GET");
            //int responseCode = connection.getResponseCode();
            
            InputStream is = connection.getInputStream();
            Scanner scanner = new Scanner(is);
            String resultado = scanner.nextLine();
            System.out.println(resultado);
            
            // parsing
            JSONObject jSONObject = new JSONObject(resultado);
            Naipe trunfo = Naipe.valueOf(jSONObject.getString("trunfo"));
            
            Map<Posicao,List<Carta>> cartas = new EnumMap<>(Posicao.class);
            
            for(Posicao posicao : Posicao.values()){
                JSONArray array = jSONObject.getJSONArray(posicao.toString());
                cartas.put(posicao, new LinkedList<>());
                for(int i=0;i<array.length();i++){
                    String cartaString = array.getString(i);
                    Carta carta = new Carta.StringAdpater().unmarshal(cartaString);
                    cartas.get(posicao).add(carta);
                }
            }
            
            Bolsa bolsa = new Bolsa(trunfo, cartas.get(Posicao.NORTH), cartas.get(Posicao.EAST), cartas.get(Posicao.SOUTH), cartas.get(Posicao.WEST), Posicao.valueOf(dador));
            System.out.println(bolsa);
            connection.disconnect();            
        } catch (MalformedURLException ex) {
            Logger.getLogger(GeradorAleatorioQrngAnu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeradorAleatorioQrngAnu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(UsandoWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UsandoWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
