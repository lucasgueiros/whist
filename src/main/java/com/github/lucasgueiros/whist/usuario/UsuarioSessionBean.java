/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.usuario;

import com.github.lucasgueiros.whist.util.SaidaParaArquivo;
import com.github.lucasgueiros.whist.util.propriedades.PropriedadesApplicationBean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lucasgueiros.whist.util.repositorio.Repositorio;
import com.github.lucasgueiros.whist.util.repositorio.RepositorioJPA;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author lucas
 */

@ManagedBean ( eager=true)
@SessionScoped
public class UsuarioSessionBean implements Serializable {
	
	// Logger
	private final Logger logger;
	
    private FacesContext facesContext = FacesContext.getCurrentInstance();
    private String login;
    private String senha;
    private Usuario jogador;
    private String nome;
    
    private Repositorio<Usuario> repositorioJogador;
    
    public UsuarioSessionBean() {
		//DaoManagerHiber.main(null);
    	repositorioJogador = new RepositorioJPA<Usuario>(Usuario.class);
    	logger = LoggerFactory.getLogger(UsuarioSessionBean.class);
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
    	//logger.error("OPAAAHHH");
        //jogador = RepositoryFactory.getRepositorioJogador().recuperarPorLogin(login);
        List<Usuario> consulta = (List<Usuario>) repositorioJogador.recuperar(new FiltroUsuarioLogin(login));
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
            facesContext.addMessage(null, new FacesMessage("Falha de autenticacao.","JogadorSessionBean::autenticar"));
            SaidaParaArquivo.file.println("JogadorSessionBean::autenticar");
            return PropriedadesApplicationBean.getString("pagina.deErro");
        } else {
            return PropriedadesApplicationBean.getString("pagina.index"); //  //$NON-NLS-1$
        }
    }
    
    public String finalizarCadastrar() {
        //try RepositoryFactory.getRepositorioJogador().adicionar(jogador);
        this.jogador = new Usuario(nome, login, senha);
        this.repositorioJogador.adicionar(jogador);        
        return PropriedadesApplicationBean.getString("pagina.index");//autenticar(); //$NON-NLS-1$
    }
    
    public String cadastro() {
        return PropriedadesApplicationBean.getString("pagina.login.paraCompletarOCadastro"); //$NON-NLS-1$
    }
    
    public String getUsuarioLabel() {
        return "Login: " + nome; //$NON-NLS-1$
    }
    
    public Usuario getJogador() {
        if(!isAutenticado()) return null; // daí dá pra testar se está autenticado pelo retorno desse método.
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
        return PropriedadesApplicationBean.getString("pagina.index"); //$NON-NLS-1$
    }
    
}
