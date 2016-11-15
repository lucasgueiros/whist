/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.jogador;

import com.github.lucasgueiros.ifuwhist.util.propriedades.Propriedades;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lucasgueiros.ifuwhist.util.repositorio.Repositorio;
import com.github.lucasgueiros.ifuwhist.util.repositorio.RepositorioJPA;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author lucas
 */

@ManagedBean ( eager=true )
@SessionScoped
public class ContraladorAutenticacao implements Serializable {
	
	// Logger
	private final Logger logger;
	
    private String login;
    private String senha;
    private Jogador jogador;
    private String nome;
    
    private Repositorio<Jogador> repositorioJogador;
    
    public ContraladorAutenticacao() {
		//DaoManagerHiber.main(null);
    	repositorioJogador = new RepositorioJPA<Jogador>(Jogador.class);
    	logger = LoggerFactory.getLogger(ContraladorAutenticacao.class);
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String autenticar()  {
    	logger.error("OPAAAHHH");
        //jogador = RepositoryFactory.getRepositorioJogador().recuperarPorLogin(login);
        List<Jogador> consulta = (List<Jogador>) repositorioJogador.recuperar(new FiltroJogadorLogin(login));
        if(consulta.size()>0) {
            jogador = consulta.get(0);
        }
    	//Filtro<Jogador> filtro = new FiltroJogadorLogin(login);
    	//List<Jogador> resultado = repositorioJogador.recuperar(filtro);
    	//jogador = resultado.get(0);
        if(jogador == null) {
        	logger.debug("Lancando RuntimeException jogador==null ControladorAutenticacao#getInstance()"); //$NON-NLS-1$
            throw new RuntimeException("jogador==null"); //$NON-NLS-1$
            //return "errorpage.xhtml"; // TODO coloque o erro aqiu
        } else if (!jogador.autenticar(senha)) {
            return Propriedades.getString("pagina.deErro"); // TODO coloque o erro aqiu //$NON-NLS-1$
        } else {
            return Propriedades.getString("pagina.index"); //  //$NON-NLS-1$
        }
    }
    
    public String finalizarCadastrar() {
        //try RepositoryFactory.getRepositorioJogador().adicionar(jogador);
        this.jogador = new Jogador(nome, login, senha);
        this.repositorioJogador.adicionar(jogador);        
        return Propriedades.getString("pagina.index");//autenticar(); //$NON-NLS-1$
    }
    
    public String cadastro() {
        return Propriedades.getString("pagina.paraCompletarOCadastro"); //$NON-NLS-1$
    }
    
    public String getUsuarioLabel() {
        return "Login: " + nome; //$NON-NLS-1$
    }
    
    public Jogador getJogador() {
        return jogador;
    }
    
    public boolean isAutenticado() {
        return jogador != null;
    }
    
    public String sair() {
        this.login = null;
        this.nome = null;
        this.senha = null;
        this.jogador = null;
        // TODO
        return Propriedades.getString("pagina.index"); //$NON-NLS-1$
    }
    
}
