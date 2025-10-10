package org.lessons.java.spring_film.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/films/create", "/films/edit/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/films/**").hasAuthority("ADMIN")
            .requestMatchers("/films/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/genres/create", "/genres/edit/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/genres/**").hasAuthority("ADMIN")
            .requestMatchers("/genres/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/", "/**").permitAll())
        .csrf(csrf -> csrf.disable())
        .cors(Customizer.withDefaults())
        .formLogin(Customizer.withDefaults())
        .logout(Customizer.withDefaults())
        .exceptionHandling(Customizer.withDefaults());

    return http.build();

  }

  @Bean
  @SuppressWarnings("deprecation")
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailService());

    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  DatabaseUserDetailService userDetailService() {
    return new DatabaseUserDetailService();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
