package br.com.projeto.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.projeto.enums.chamado.Status;
import br.com.projeto.model.Chamado;
import br.com.projeto.service.ChamadoService;

@Named
@Path("chamados")
public class ChamadoResource {
	
	ChamadoService service = new ChamadoService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Chamado>listChamados(){
		
		try {	
			List<Chamado> chamados = service.listar();
			return chamados;
		} catch (Exception e) {
			Logger.getLogger(ChamadoResource.class.getClass().getName()).log(Level.SEVERE, null,e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Chamado getChamado (@PathParam("id") Long id) {
		try {
			Chamado chamado = service.selecionar(id);
			return chamado;
		} catch (Exception e) {
			Logger.getLogger(ChamadoResource.class.getClass().getName()).log(Level.SEVERE, null,e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(Chamado chamado) {
		try {

			service.inserir(chamado);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(ChamadoResource.class.getClass().getName()).log(Level.SEVERE, null,e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response update(Chamado chamado) {
		try {
			chamado.setStatus(Status.PENDENTE);
	
			service.alterar(chamado);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(ChamadoResource.class.getClass().getName()).log(Level.SEVERE, null,e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		try {

			service.excluir(id);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(ChamadoResource.class.getClass().getName()).log(Level.SEVERE, null,e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PUT
	@Path("{id}")
	public Response concluir(@PathParam("id") Long id) {
		try {
			Chamado chamado = service.selecionar(id);
			chamado.setStatus(Status.FECHADO);
			service.concluir(chamado);
			
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			Logger.getLogger(ChamadoResource.class.getClass().getName()).log(Level.SEVERE, null,e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
