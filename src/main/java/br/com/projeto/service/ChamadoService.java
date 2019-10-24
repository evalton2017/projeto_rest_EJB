package br.com.projeto.service;

import java.util.Date;
import java.util.List;


import br.com.projeto.dao.ChamadoDao;
import br.com.projeto.enums.chamado.Status;
import br.com.projeto.enums.chamado.Tipo;
import br.com.projeto.model.Chamado;


public class ChamadoService {
	

	private UsuarioService userService = new UsuarioService();
	 
	private ChamadoDao chamadoDao = new ChamadoDao();
	
	public Long inserir(Chamado chamado) {
		chamado.setDataRegistro(new Date());
		chamado.setStatus(Status.NOVO);
		chamado.setTipo(Tipo.SOLICITACAO);
		chamado.setUsuario(UsuarioService.selecionarAluno());
		chamado.setUsuarioStatus(UsuarioService.selecionarAluno());
		
		chamadoDao.inserir(chamado);
			
		return chamado.getId();
	}

	public void alterar(Chamado chamado) {
			
		chamadoDao.alterar(chamado);
	}
	
	public void excluir(Long id) {
		
		chamadoDao.excluir(id);
		
	}
	
	public Chamado selecionar(Long id) {
		
		Chamado chamado = chamadoDao.selecionar(id);
		return chamado;

	}
	
	public void concluir(Chamado chamado) {
		
		chamado.setUsuario(userService.selecionarAluno());
		chamado.setUsuarioStatus(chamado.getUsuario());
		chamadoDao.concluir(chamado);

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Chamado> listar() {
		List<Chamado> chamados = chamadoDao.listar();
		return chamados;
	}

}

