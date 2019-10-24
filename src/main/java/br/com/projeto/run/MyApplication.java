package br.com.projeto.run;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.com.projeto.filter.CorsFilter;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
		
	public MyApplication() {
		register( new CorsFilter() );
		packages("br.com.projeto.rest");
	}


}

