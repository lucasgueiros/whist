package com.github.lucasgueiros.ifuwhist.util.propriedades;

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

    public String get(String key) {
        return PropriedadesApplicationBean.getString(key);
    }
    
}
