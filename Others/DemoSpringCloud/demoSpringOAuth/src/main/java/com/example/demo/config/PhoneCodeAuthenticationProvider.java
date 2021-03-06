package com.example.demo.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class PhoneCodeAuthenticationProvider implements AuthenticationProvider  {
	 private final String adminName = "root";
	 private final String code = "123456";
	    
	    private final List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("CAN_SEARCH"),
	            new SimpleGrantedAuthority("CAN_SEARCH"),
	            new SimpleGrantedAuthority("CAN_EXPORT"),
	            new SimpleGrantedAuthority("CAN_IMPORT"),
	            new SimpleGrantedAuthority("CAN_BORROW"),
	            new SimpleGrantedAuthority("CAN_RETURN"),
	            new SimpleGrantedAuthority("CAN_REPAIR"),
	            new SimpleGrantedAuthority("CAN_DISCARD"),
	            new SimpleGrantedAuthority("CAN_EMPOWERMENT"),
	            new SimpleGrantedAuthority("CAN_BREED"));
	    
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 if(isMatch(authentication)){
	            User user = new User(authentication.getName(),authentication.getCredentials().toString(),authorities);
	            return new UserPhoneCodeAuthenticationToken(user,authentication.getCredentials(),authorities);
	      }
	      return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return UserPhoneCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}
	
	 private boolean isMatch(Authentication authentication){
       if(authentication.getName().equals(adminName)&&authentication.getCredentials().equals(code))
           return true;
       else
           return false;
   }
}
