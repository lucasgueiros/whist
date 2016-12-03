/**
 * 
 */
package com.github.lucasgueiros.whist.util.repositorio;


import com.github.lucasgueiros.whist.testUtil.DBUnitUtil;
import org.junit.Before;
import org.junit.BeforeClass;

import com.github.lucasgueiros.whist.usuario.Usuario;

/**
 * @author lucas
 *
 */

public class RepositorioDBUsuarioTestIT {
	
	private static DBUnitUtil dbUnitUtil;
	//private RepositorioDBUsuario repositorio;
	private Repositorio<Usuario> repositorio;
	

	/**
	 * 
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		String endereco = "src/test/resources/UsuarioDataset.xml";
		//DaoManagerHiber.main(null);
		dbUnitUtil = new DBUnitUtil(endereco);
	}

	public static void tearDownAfterClass() {
		dbUnitUtil.executeDelete();
		dbUnitUtil.close();
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dbUnitUtil.executeCleanInsert();
		repositorio = new RepositorioJPA<>(Usuario.class);
	}

	/*@Test
	public void testRecuperarPorLogin() {
		// variáveis
		String login = "firefox";
		
		// executando
		Usuario resultado = this.repositorio.recuperar(new FiltroUsuarioLogin<>(login));
		
		// testando se tá tudo ok!
		assertEquals(login,resultado.getLogin());
	}*/

}
