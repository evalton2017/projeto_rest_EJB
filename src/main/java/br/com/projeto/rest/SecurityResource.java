package br.com.projeto.rest;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("secured")
public class SecurityResource {

	@GET
	@Path("message")
	@Produces(MediaType.TEXT_PLAIN)
	public String autenticacao() {
		return "API para login";
	}
}
