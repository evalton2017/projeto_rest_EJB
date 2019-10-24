package br.com.projeto.rest;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.core.util.Base64;


@Provider
public class FilterResource implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured";
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {		
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			
//			String teste="login:senha";
//			String codificar = new String(Base64.encode(teste));
			
			if(authHeader != null && authHeader.size()>0) {
				String authToken = (String)authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodeString = new String(Base64.decode(authToken));
				StringTokenizer tokenizer = new StringTokenizer(decodeString, ":");
				String login = tokenizer.nextToken();
				String senha = tokenizer.nextToken();
				
				if("login".equals(login) && "senha".equals(senha)) {
					return;
				}
			}
			
			Response unautorizedStatus = Response
					.status(Response.Status.UNAUTHORIZED)
					.entity("Usuario não possuio autorização para acessar")
					.build();
			
			requestContext.abortWith(unautorizedStatus);
		}
	}

}
