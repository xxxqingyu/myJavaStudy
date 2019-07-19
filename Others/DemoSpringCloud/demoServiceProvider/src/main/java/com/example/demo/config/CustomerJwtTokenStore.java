package com.example.demo.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class CustomerJwtTokenStore extends JwtTokenStore {

	private RedisTemplate<String, Object> redisTemplate;

	public CustomerJwtTokenStore(JwtAccessTokenConverter jwtTokenEnhancer,
			RedisTemplate<String, Object> redisTemplate) {
		super(jwtTokenEnhancer);
		this.redisTemplate = redisTemplate;
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OAuth2AccessToken token = super.readAccessToken(tokenValue);
		if(isDisabled(tokenValue)){
			throw new InvalidTokenException("token is expired");
		}
		return token;
	}

	private boolean isDisabled(String tokenValue) {
		String[] values = tokenValue.split("\\.");
		if (values.length == 3) {
			String hashKey = values[2];
			return redisTemplate.hasKey(hashKey);
		}
		return false;
	}
}
