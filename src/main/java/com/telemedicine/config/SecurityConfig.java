package com.telemedicine.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public ClientDetailsService clientDetailsService;


    @Autowired
    public CrmUserDetailsService crmUserDetailsService;


    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(crmUserDetailsService).passwordEncoder(encoder());
    }

    /*
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {

        String password = encoder().encode("crmpass");
        String passwordUser = encoder().encode("pass123");
        System.out.println("passwork8==>"+password);
        auth.inMemoryAuthentication()
                .withUser("crmadmin").password(password).roles("ADMIN","USER").and()
                .withUser("crmuser").password(passwordUser).roles("USER");

    }
    */


    @Override
    @Order(Ordered.HIGHEST_PRECEDENCE)
    protected void configure(HttpSecurity http) throws Exception {

        http    //.cors().and()
                .sessionManagement()
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**","/signup/**","/layout/**").permitAll()
                .antMatchers("/img/**","/js/*.js","/css/*.css","/favicon.ico").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/oauth/authorize").permitAll()
                .antMatchers("/api/**","/admin/**","/user/**","/test/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/**","/user/**","/test/**").hasAnyRole("ADMIN","USER")

                //.antMatchers("/api/userinfo").hasRole("ADMIN")
                //.anyRequest().authenticated()
                //.and().httpBasic().realmName("CRM_REALM")
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/signin")
                .usernameParameter("userid").passwordParameter("password")
                .and().formLogin().defaultSuccessUrl("/test")
                .and().exceptionHandling().accessDeniedPage("/login/index.html")
                .and().csrf().disable();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }


    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }



    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
