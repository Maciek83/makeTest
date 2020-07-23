package com.gosciminski.testsapp.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/*").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("api/login").permitAll()
        .antMatchers("api/register").permitAll()
        .antMatchers("api/question").hasRole("USER")
        .antMatchers("api/question/{id}").hasRole("USER")
        .antMatchers("api/test").hasRole("USER")
        .antMatchers("api/test/{id}").hasRole("USER")
        .antMatchers("api/testshare").hasRole("USER")
        .and()
        .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .csrf().disable();

        http.headers().frameOptions().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "*.bundle.*");
    }
    
}