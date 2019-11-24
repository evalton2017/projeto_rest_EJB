package br.com.projeto.rest;

import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Set;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.security.auth.Subject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.security.SecurityContext;
import org.jboss.security.SecurityContextAssociation;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;

@Stateless
@Remote(RemoteHello.class)
@RolesAllowed({ "user" })
@SecurityDomain("keycloak-ejb")
public class ApiSecuryti implements RemoteHello {

	@Resource
	private SessionContext ctx;


	@Override
	public String user() {
		Principal principal = ctx.getCallerPrincipal();
		return "Simple - Hello " + principal.getName();
	}

	@Override
	public String admin() {
		Principal principal = ctx.getCallerPrincipal();

		Subject subject = getSecurityContext().getSubjectInfo().getAuthenticatedSubject();
		Set<KeycloakPrincipal> keycloakPrincipals = subject.getPrincipals(KeycloakPrincipal.class);
		KeycloakPrincipal kcPrincipal = keycloakPrincipals.iterator().next();
		AccessToken accessToken = kcPrincipal.getKeycloakSecurityContext().getToken();

		return "Advanced - Hello " + accessToken.getName();
	}
	
	 private SecurityContext getSecurityContext() {
	        return AccessController.doPrivileged(new PrivilegedAction<SecurityContext>() {
	            @Override
	            public SecurityContext run() {
	                return SecurityContextAssociation.getSecurityContext();
	            }
	        });
	    }

}
