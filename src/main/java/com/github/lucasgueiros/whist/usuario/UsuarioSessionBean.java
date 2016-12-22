/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.usuario;

import com.github.lucasgueiros.whist.util.SaidaParaArquivo;
import com.github.lucasgueiros.whist.util.propriedades.PropriedadesApplicationBean;
import com.github.lucasgueiros.whist.util.repositorio.FiltroRecuperarTodos;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lucasgueiros.whist.util.repositorio.Repositorio;
import com.github.lucasgueiros.whist.util.repositorio.RepositorioJPA;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author lucas
 */

@ManagedBean ( eager=true)
@SessionScoped
public class UsuarioSessionBean implements Serializable {
	
	// Logger
	private final Logger logger;
	
    private String login;
    private String senha;
    private Usuario jogador;
    private String nome;
    private String senha2;
    
    private Repositorio<Usuario> repositorio;
    
    public UsuarioSessionBean() {
		//DaoManagerHiber.main(null);
    	repositorio = new RepositorioJPA<Usuario>(Usuario.class);
        repositorio.recuperar(new FiltroRecuperarTodos()); // para carregar logo essa lista!
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
    	List<Usuario> consulta = (List<Usuario>) repositorio.recuperar(new FiltroUsuarioLogin(login));
        
        if(consulta.isEmpty() || ! consulta.get(0).autenticar(senha)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Falha de autenticacao.","Login ou senha incorretos."));
            return "";
        }
        jogador = consulta.get(0);
        return PropriedadesApplicationBean.getString("pagina.index"); //  //$NON-NLS-1$
    }
    
    public String finalizarCadastrar() {
        //try RepositoryFactory.getRepositorioJogador().adicionar(jogador);
        this.jogador = new Usuario(nome, login, senha);
        this.repositorio.adicionar(jogador);        
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

    public String getSenha2() {
        return senha2;
    }

    public void setSenha2(String senha2) {
        this.senha2 = senha2;
    }
    public void validarUsuarioComSenha(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
    }
    
    public void validarUsuario(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String login = (String) value;
        if(!repositorio.recuperar(new FiltroUsuarioLogin(login)).isEmpty()){
            FacesMessage message = new FacesMessage("Login em uso :/");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        } 
    }
    
}
