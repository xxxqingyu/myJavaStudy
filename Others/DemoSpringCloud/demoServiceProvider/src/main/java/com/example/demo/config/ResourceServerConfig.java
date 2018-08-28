package com.example.demo.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
    @Value("${security.oauth2.authorization.checkTokenAccess}")
    String checkTokenUri;

    @Value("${security.oauth2.client.clientId}")
    String clientId;
    
    @Value("${security.oauth2.client.clientSecret}")
    String clientSecret;
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/hello").authenticated().and().authenticationProvider(jwtRevokeAuthenticationProvider());
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//resources.tokenServices()
		//resources.
		resources.tokenServices(tokenServices());
	}
	
	@Bean
	public JwtRevokeAuthenticationProvider jwtRevokeAuthenticationProvider(){
		return new JwtRevokeAuthenticationProvider();
	}
	
	
	@Bean
	public ResourceServerTokenServices tokenService() {
	   RemoteTokenServices tokenServices = new RemoteTokenServices();
	   tokenServices.setClientId(clientId);
	   tokenServices.setClientSecret(clientSecret);
	   tokenServices.setCheckTokenEndpointUrl(checkTokenUri);
	   return tokenServices;
	}
	
    @Bean
    public TokenStore tokenStore() {
        return new CustomerJwtTokenStore(accessTokenConverter(), redisTemplate);
    }
 
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }
 
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

	

	
}
