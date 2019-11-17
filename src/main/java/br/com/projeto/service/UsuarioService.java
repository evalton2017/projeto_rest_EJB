package br.com.projeto.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.projeto.dao.UsuarioDao;
import br.com.projeto.enums.usuario.Tipo;
import br.com.projeto.model.Usuario;
import br.com.projeto.util.HibernateUtil;


public class UsuarioService {

	EntityManager entityManager = HibernateUtil.getEntityManager();

	private static UsuarioDao dao = new UsuarioDao();
	
	public static Usuario selecionarAluno() {
		Usuario aluno = dao.login("aluno");
		
		if(aluno.getId() ==null) {
			aluno = new Usuario();
			aluno.setLogin("aluno");
			aluno.setSenha(DigestUtils.sha256Hex("123"));
			aluno.setNome("Aluno Teste");
			aluno.setTipo(Tipo.SUPORTE);
			aluno.setDataRegistro(new Date());
			aluno.setAtivo(true);
			
			dao.inserir(aluno);
		}
		
		return aluno;
	}
	
	public Long inserir(Usuario usuario) {
		
		dao.inserir(usuario);		
		return usuario.getId();

	}
	
	public Usuario selecionar(long id) {
		Usuario user = dao.selecionar(id);		
		return  user;
	}
	
	public List<Usuario> listart(){	
		List<Usuario> users = new ArrayList<>();
		users = dao.listar();
		
		return users;
		
	}
	
}

