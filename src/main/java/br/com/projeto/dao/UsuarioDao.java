package br.com.projeto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.projeto.model.Usuario;
import br.com.projeto.util.HibernateUtil;


public class UsuarioDao {

	EntityManager entityManager = HibernateUtil.getEntityManager();
	
	public List<Usuario> findAll() {
		return entityManager.createQuery("from Usuario u ORDER BY u.id", Usuario.class).getResultList();
	}
	
	public Usuario buscar(String login) {
		Usuario user = new Usuario();
		//	transaction.begin();
			Query query =entityManager.createQuery("from Usuario where login = :login");
			
			query.setParameter("login", login);
		//	Usuario usuario = (Usuario) query.getSingleResult();
			List<Usuario>  usuario = (List<Usuario>) query.getResultList();
			
			if(usuario.isEmpty()) {
				user = new Usuario();
			}else {
				user = usuario.get(0);
			}
			return user;
	}
	
	public List<Usuario> listar(){
		List<Usuario> usuarios = new ArrayList<>();
		Query query =entityManager.createQuery("from Usuario order by nome");
		
		usuarios= (List<Usuario>) query.getResultList();
		return usuarios;
	}
	
	
	public Long inserir(Usuario usuario) {
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(usuario);
		transaction.commit();
		
		return usuario.getId();

	}
	
	public Usuario selecionar(long id) {
		EntityTransaction transaction  = entityManager.getTransaction();
		transaction.begin();
		
		Usuario usuario = (Usuario) entityManager.createQuery("from Usuario where id = :id")
				.setParameter("id", id).getResultList();

		return usuario;
	}
	
	public Usuario login(String login) {
		//EntityTransaction transaction  = entityManager.getTransaction();
		//transaction.begin();

		Usuario user = new Usuario();
		//	transaction.begin();
		Query query =entityManager.createQuery("from Usuario where login = :login");
		query.setParameter("login", login);
		//	Usuario usuario = (Usuario) query.getSingleResult();
		List<Usuario>  usuario = (List<Usuario>) query.getResultList();
			if(usuario.isEmpty()) {
				user = new Usuario();
			}else {
				user = usuario.get(0);
			}
			return user;
//		Usuario usuario = (Usuario) entityManager.createQuery("from Usuario where login = :login")
//				.setParameter("login", login).getResultList();
	}
	

}
