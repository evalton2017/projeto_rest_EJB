package br.com.projeto.teste;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.projeto.dao.ChamadoDao;
import br.com.projeto.dao.UsuarioDao;
import br.com.projeto.enums.usuario.Tipo;
import br.com.projeto.model.Chamado;
import br.com.projeto.model.Usuario;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class UsuarioTest {
	UsuarioDao dao = new UsuarioDao();
	ChamadoDao cdao = new ChamadoDao();
	
	@Before
	public void setUp() throws Exception {
	}

	@Ignore
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void inserir() {
		
		
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setDataRegistro(new Date());
		usuario.setLogin("duke");
		usuario.setNome("Duke Gomes");
		usuario.setSenha("123");
		usuario.setTipo(Tipo.ADMIN);
		
		dao.inserir(usuario);
	}
	
	@Ignore
	public void listar() {
		List<Usuario> user = dao.findAll();
		System.out.println(user);
		
	}
	
	@Ignore
	public void listarChamado() {
		cdao.excluir(4L);
		System.out.println("Excluiu");
	}
	
	

}
