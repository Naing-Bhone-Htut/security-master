package com.example.securitymaster.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import static com.example.securitymaster.security.SecurityRoles.*;

@Configuration

public class WebSecurityConfig {

    @Autowired
    private RoleHierarchy roleHierarchy;

    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();
        var john = User.withUsername("John").password("john").roles(ROLES_ADMIN).build();
        var emma = User.withUsername("emma").password("emma").roles(EMPLOYEES_ADMIN).build();
        var william = User.withUsername("william").password("william").roles(DEPARTMENTS_CREATE, DEPARTMENTS_READ, DEPARTMENTS_PAG_VIEW).build();
        var lucas = User.withUsername("lucas").password("lucas").roles(CUSTOMERS_READ, CUSTOMERS_PAG_VIEW).build();
        var tom = User.withUsername("tom").password("tom").roles().build();

        uds.createUser(john);
        uds.createUser(emma);
        uds.createUser(william);
        uds.createUser(lucas);
        uds.createUser(tom);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable {

        http.authorizeRequests()
                .expressionHandler(expressionHandler())
                .requestMatchers("/", "/home", "/bootstrap/**")
                .permitAll()
                .requestMatchers("/customers/**")
                .hasRole(CUSTOMERS_PAG_VIEW)
                .requestMatchers("/employees/**")
                .hasRole(EMPLOYEES_PAG_VIEW)
                .requestMatchers("/departments/**")
                .hasRole(DEPARTMENTS_PAG_VIEW)
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler(){
        DefaultWebSecurityExpressionHandler handler=new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

}
