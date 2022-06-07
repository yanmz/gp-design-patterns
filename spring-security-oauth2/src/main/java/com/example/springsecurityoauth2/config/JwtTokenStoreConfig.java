package com.example.springsecurityoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Map;

/**
 * @author :xiaoyan
 * @description :
 * @create :2022-06-06 22:35:00
 */
public class JwtTokenStoreConfig {

    @Bean
    public TokenStore tokenStore(){
        return new  JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("test_key");
//        JwtClaimsSetVerifier jwtClaimsSetVerifier = new JwtClaimsSetVerifier() {
//            @Override
//            public void verify(Map<String, Object> claims) throws InvalidTokenException {
//                claims.put("","");
//                claims.put("","");
//                claims.put("","");
//                claims.put("","");
//            }
//        };

//        jwtAccessTokenConverter.setJwtClaimsSetVerifier(jwtClaimsSetVerifier);
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer(){
        return  new JwtTokenEnhancer();
    }
}
