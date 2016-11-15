/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.lucasgueiros.ifuwhist.resultados;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lucasgueiros.ifuwhist.jogador.JogadorSessionBean;
import com.github.lucasgueiros.ifuwhist.jogador.Jogador;
import com.github.lucasgueiros.ifuwhist.util.repositorio.FiltroRecuperarTodos;
import com.github.lucasgueiros.ifuwhist.util.repositorio.RepositorioJPA;
import java.io.Serializable;

/**
 *
 * @author lucas
 */
@ManagedBean
@SessionScoped
public class ResultadosSessionBean  implements Serializable {
    
	// Logger
	private Logger logger = LoggerFactory.getLogger(getClass());
	
    
    public List<Resultado> getResultados(Jogador jogador) {
        //long id = auth.getJogador().getId();
        //return RepositoryFactory.getRepositorioPartidaTerminada().recuperarPorId(id);
        
        // eu sei, isso não deveria estar aqui.
        // mas eu tenho mais o que fazer da vida e vai ser assim mesmo.
        // TODO Tire isso daqui
    	
    	//FacesContext faces = FacesContext.getCurrentInstance();
        //auth = (JogadorSessionBean) faces.getApplication().evaluateExpressionGet(faces, "#{contraladorAutenticacao}", JogadorSessionBean.class);
    	
        //Jogador jogador = auth.getJogador();
//List<Resultado> all = RepositoryFactory.getRepositorioPartidaTerminada().recuperar();
        List<Resultado> recuperados = new RepositorioJPA<Resultado>(Resultado.class).recuperar(new FiltroContemJogador(jogador));/*new FiltroRecuperarTodos<>());
        for (Iterator<Resultado> it = recuperados.iterator(); it.hasNext();) {
            Resultado match = it.next();
            if(!match.containsJogador(p)) {
                it.remove();
            }
        }*/
        return recuperados;
        
        //System.out.println("ISSO É ALGO BEM ESCANDALOSO");
        //RepositoryFactory.getRepositorioPartidaTerminada().recuperar().forEach(m -> System.out.println("MATCH: " + m));
        
        //return RepositoryFactory.getRepositorioPartidaTerminada().recuperar();
        /*
        List<PartidaTerminada> toreturn = new LinkedList<>();
        
        for (Object match : DaoManagerHiber.getInstance().recover("from PartidaTerminada")) {
            toreturn.add((PartidaTerminada)  match);
        }
        return toreturn;*/
        
    }
    
    // TODO Altere essa forma de processamento. Não deveria ficar no JavaBean
	public Collection<Jogador> getRanking() {
		logger.debug("Iniciando getRanking!");
		// Estratégia: recupere todos os jogadores
		// jogador por jogador, calcule programaticamente a pontuacao dele e der SET
		// retorne a lista, no JSF ordene!
		
		// Recuperando os resultados
		List<Resultado> resultados = new RepositorioJPA<Resultado>(Resultado.class).recuperar(new FiltroRecuperarTodos<Resultado>());
		logger.debug("Quantidade de resultados recuperados: " + resultados.size());
		
		// Recuperando jogadores
		// List<Jogador> jogadores = new RepositorioJPA<Jogador>(Jogador.class).recuperar(new FiltroRecuperarTodos<>());
		// Essa forma de recuperar os jogadores não dá certo!
		// Além de ser mais custosa (faz uma consulta ao BD), ela cria novas instancias dos jogadores, deixando duplicatas
		// É melhor pegar os jogadores que vieram junto com os resultados:
		// O único problema é que jogadores que não jogaram nada não vão aparecer.
		// Esse problema de duplicatas está na issue #5
		Set<Jogador> jogadores = new HashSet<>();
		
		// Tirando jogadores dos resultados
		for(Resultado resultado : resultados) {
			jogadores.add(resultado.getEast());
			jogadores.add(resultado.getWest());
			jogadores.add(resultado.getNorth());
			jogadores.add(resultado.getSouth());
		}
		
		logger.debug("recuperando os jogadores do BD. " + jogadores.size() + " jogadores recuperados.");
		for(Jogador jogador : jogadores){
			jogador.setPontuacao(0);
		}
		
		for(Resultado resultado : resultados) {
			//logger.debug("Debugando 0: ANTES login: " + jogadores.get(0).getLogin() + " pontuacao: " + jogadores.get(0).getPontuacao());
			resultado.getNorth().setPontuacao(resultado.getNorth().getPontuacao() + resultado.getPointsNS());
			//logger.debug("Debugando 0: DEPOIS login: " + jogadores.get(0).getLogin() + " pontuacao: " + jogadores.get(0).getPontuacao());
			resultado.getSouth().setPontuacao(resultado.getSouth().getPontuacao() + resultado.getPointsNS());
			resultado.getEast().setPontuacao(resultado.getEast().getPontuacao() + resultado.getPointsEW());
			resultado.getWest().setPontuacao(resultado.getWest().getPontuacao() + resultado.getPointsEW());
		}
		//logger.debug("Debugando 0: AO FINAL login: " + jogadores.get(0).getLogin() + " pontuacao: " + jogadores.get(0).getPontuacao());
		return jogadores;
        /*return DaoManagerHiber.getInstance().recoverSQL("select login, sum(pontos) as pontuacao "
        		+ " from ((select login, sum(pointsns) as pontos from partida join jogador on jogador.id = south_id group by login,south_id)"
                + " union (select login, sum(pointsns) as pontos from partida join jogador on jogador.id = north_id group by login,north_id)"
                + " union (select login, sum(pointsew) as pontos from partida join jogador on jogador.id = east_id  group by login,east_id)"
                + " union (select login, sum(pointsew) as pontos from partida join jogador on jogador.id = west_id  group by login,west_id)"
                + " ) as consulta group by login order by pontuacao desc");*/
                
        }
    
    
    
}
