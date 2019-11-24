package br.com.projeto.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.projeto.enums.chamado.Status;
import br.com.projeto.model.Chamado;
import br.com.projeto.service.UsuarioService;
import br.com.projeto.util.HibernateUtil;

@Named
public class ChamadoDao {
			
	EntityManager entityManager = HibernateUtil.getEntityManager();
	
	@Inject
	private UsuarioService usuarioservice;


	public List<Chamado> listar() {
		List<Chamado> chamados = new ArrayList<Chamado>();
		chamados =  entityManager.createQuery("FROM Chamado c ORDER BY c.id", Chamado.class).getResultList();
		return chamados;
	}
	
	
	public void inserir(Chamado chamado) {
	
		if(chamado.getId()!=null) {
			alterar(chamado);
		}else {
			EntityTransaction transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(chamado);
			transaction.commit();
		}
		

	}

	public void alterar(Chamado chamado) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		chamado.setStatus(Status.PENDENTE);
		entityManager.merge(chamado);
		transaction.commit();

	}
	
	public void excluir(Long id) {
		//Chamado c = selecionar(id);
	   EntityTransaction transaction = entityManager.getTransaction();
	   transaction.begin();
       Query query = entityManager.createQuery("DELETE FROM Chamado c WHERE c.id = :id ");
       query.setParameter("id", id);
       query.executeUpdate();
	   // int rowsDeleted = query.executeUpdate();
	   // System.out.println("entities deleted: " + rowsDeleted);
	   transaction.commit();
					
	}
	
	public Chamado selecionar(Long id) {
		
		EntityTransaction transaction  = entityManager.getTransaction();
		transaction.begin();
	
		Chamado chamado = (Chamado) entityManager.createQuery("from Chamado where id = :id", Chamado.class)
				.setParameter("id", id).getSingleResult();

		return chamado;

	}
	
	public void concluir(Chamado chamado) {
		EntityTransaction transaction = entityManager.getTransaction();
		chamado.setUsuario(usuarioservice.selecionarAluno());
		//chamado.setStatus(chamado.getStatus());
		chamado.setUsuarioStatus(chamado.getUsuario());
		//transaction.begin();
		entityManager.merge(chamado);
		transaction.commit();

	}
	


}
