package com.telemedicine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//-- define URL patterns to enable OAuth2 security
        //OAuth 인증을 받도록 하는 역할
		http.
				anonymous().disable().csrf().disable()
				.requestMatchers().antMatchers("/api/**")
				.and().authorizeRequests()
				.antMatchers("/api/userinfo/**","/api/member/**","/api/telemedicines/**").access("hasRole('ADMIN') ")
				.antMatchers("/api/**").access("hasRole('ADMIN') or hasRole('USER') ")
				.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

	}
/*
	@Bean
	public TokenStore JdbcTokenStore(DataSource dataSource) {
		return new JdbcTokenStore(dataSource);
	}
*/

}
