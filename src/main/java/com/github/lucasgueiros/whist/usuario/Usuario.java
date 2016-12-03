/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.whist.usuario;

import com.github.lucasgueiros.whist.jogador.Jogador;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * Essa classe descreve um jogador verdadeiro, i.e., que equivale a um usuário
 * do sistema e por isso tem login e senha, tem um identificador (como todas as
 * entidades, além de um atributo que guarda quando ele entrou no sistema.
 * 
 * @author lucas
 */
@Entity
public class Usuario implements Jogador {
    
    @Column(name="id")
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String nome;
    @Column (nullable=false, unique=true)
    private String login;
    @Column
    private String senha;
    @Column
    private Date inicio;
    @Transient
    private int pontuacao;
    
    // TODO tire esse deprecated que é muito chato
    //@Deprecated
    Usuario() {
    }

    
    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.inicio = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    } 

    public void alterar(Usuario t) {
        if(t==null) return;
        this.setInicio(t.getInicio());
        this.setLogin(t.getLogin());
        this.setSenha(t.getSenha());
        this.setNome(t.getNome());
    }

    public boolean autenticar(String password) {
        return this.senha.equals(password);
    }

    public boolean isFake() {
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


	public int getPontuacao() {
		return pontuacao;
	}


	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

    @Override
    public boolean isUsuario() {
        return true;
    }
 
    
    
}
