package com.github.lucasgueiros.ifuwhist.util.propriedades;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.faces.bean.ApplicationScoped;

/**
 * Essa classe serve para acessar arquivos de propriedades.
 * Pode ser acesssada estaticamente ou não!
 * @author lucas
 *
 */
@ApplicationScoped
public enum Propriedades {
	
	// Valores DEFAULT
	MENSAGENS("strings_mensagens.properties"),PAGINAS("strings_paginas.properties");
	
	private Properties properties;
	
	private Propriedades(String resource) {
		properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resource);
		try {
			if(inputStream==null)
				throw new FileNotFoundException();
			properties.load(inputStream);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} 
	}
	
	public String getString(String key){
		return properties.getProperty(key);
	}

}
