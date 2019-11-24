package br.com.projeto.model;

import org.keycloak.KeycloakSecurityContext;

public class User {
	
	private final String nome;
		
	public User (KeycloakSecurityContext context) {
		this.nome = context.getToken().getPreferredUsername();
	}

	public String getNome() {
		return nome;
	}
	

	
}
	