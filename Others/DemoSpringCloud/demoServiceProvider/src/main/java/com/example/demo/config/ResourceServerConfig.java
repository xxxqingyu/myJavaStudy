package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
    @Value("${security.oauth2.authorization.checkTokenAccess}")
    String checkTokenUri;

    @Value("${security.oauth2.client.clientId}")
    String clientId;
    
    @Value("${security.oauth2.client.clientSecret}")
    String clientSecret;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/hello").authenticated();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//resources.tokenServices()
		//resources.
	}

	
	@Bean
	public ResourceServerTokenServices tokenService() {
	   RemoteTokenServices tokenServices = new RemoteTokenServices();
	   tokenServices.setClientId(clientId);
	   tokenServices.setClientSecret(clientSecret);
	   tokenServices.setCheckTokenEndpointUrl(checkTokenUri);
	   return tokenServices;
	}
	
}
