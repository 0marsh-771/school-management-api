package com.omarapi.studentapi.Security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class StudentSecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select user_name, password, enabled from users where user_name=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_name, role from roles where user_name=?"
        );

        return jdbcUserDetailsManager;
    }

    // Block the security for swagger
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**",
                "/favicon.ico"
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/api/students/**").hasAnyRole("STUDENT", "TEACHER", "PRINCIPAL")
                                .requestMatchers(HttpMethod.POST, "/api/students").hasAnyRole("TEACHER", "PRINCIPAL")
                                .requestMatchers(HttpMethod.PUT, "/api/students").hasAnyRole("TEACHER", "PRINCIPAL")
                                .requestMatchers(HttpMethod.PATCH, "/api/students/**").hasAnyRole("TEACHER", "PRINCIPAL")
                                .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("PRINCIPAL")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}