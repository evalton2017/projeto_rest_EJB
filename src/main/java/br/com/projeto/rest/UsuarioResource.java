package br.com.projeto.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.projeto.model.Usuario;
import br.com.projeto.service.UsuarioService;

@Path("usuarios")
public class UsuarioResource {
	
	UsuarioService service = new UsuarioService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public List<Usuario> listChamados(){
		
		try {	
			List<Usuario> usuarios = service.listart();
			return usuarios;
		} catch (Exception e) {
			Logger.getLogger(ChamadoResource.class.getClass().getName()).log(Level.SEVERE, null,e);
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
	}

}
