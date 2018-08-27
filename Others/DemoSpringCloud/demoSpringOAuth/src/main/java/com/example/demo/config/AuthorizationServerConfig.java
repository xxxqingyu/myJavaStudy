package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class AuthorizationServerConfig extends  AuthorizationServerConfigurerAdapter{
	
	 @Autowired
	 private AuthenticationManager authenticationManager;
	 
	 @Autowired
	 private UserDetailsService userDetailsService;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("myMvc")
		.scopes("api")
		.secret("android")
		.authorizedGrantTypes("password","refresh_token")
		.and()
		.withClient("myApi")
		.secret("secret")
		.authorizedGrantTypes("client_credentials");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		//ResourceServerTokenServices
		security
		.allowFormAuthenticationForClients()
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("permitAll()");
	}
	
	
}
