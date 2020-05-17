package com.boot.pp25.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger log = Logger.getLogger(SecurityConfig.class.getName());
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        log.info("configureGlobalSecurity");
        auth.inMemoryAuthentication().withUser("serverSecretAdmin")
                .password(passwordEncoder.encode("secretPassword"))
                .roles("ADMIN");
        auth.inMemoryAuthentication().withUser("serverSecretUser")
                .password(passwordEncoder.encode("secretPassword"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api**").hasRole("ADMIN")
                .and()
                .httpBasic();
    }


}