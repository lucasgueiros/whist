package com.github.lucasgueiros.ifuwhist.util.propriedades;

import com.github.lucasgueiros.ifuwhist.partida.cartas.Carta;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Essa classe serve para acessar arquivos de propriedades. Pode ser acesssada
 * estaticamente ou não!
 *
 * @author lucas
 *
 */
@ManagedBean (name="propriedades")
@ApplicationScoped
public class PropriedadesApplicationBean  implements Serializable {

    private static Properties properties;
    
    static {
        properties = new Properties();
        InputStream inputStream = PropriedadesApplicationBean.class.getClassLoader().getResourceAsStream("/resources/strings.properties");
        try {
            if (inputStream == null) {
                throw new FileNotFoundException();
            }
            properties.load(inputStream);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static String getImagem(Carta carta) {
        String url = "cartas/";
        switch(carta.getSimbolo()) {
            case A: url += "ace"; break;
            case K: url += "king"; break;
            case Q: url += "queen"; break;
            case J: url += "jack"; break;            
            default: url+=carta.getSimbolo().toString();
        }
        url += "_of_";
        switch(carta.getNaipe()) {
            case CLUBS: url += "clubs"; break;
            case DIAMONDS: url += "diamonds"; break;
            case HEARTS: url += "hearts"; break;
            case SPADES: url += "spades"; break;
        }
        url += ".svg";
        return url;
    }

    public String get(String key) {
        return PropriedadesApplicationBean.getString(key);
    }
    
}
