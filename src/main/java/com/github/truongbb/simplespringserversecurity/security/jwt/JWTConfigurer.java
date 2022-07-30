package com.github.truongbb.simplespringserversecurity.security.jwt;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  TokenProvider tokenProvider;

  public JWTConfigurer(TokenProvider tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public void configure(HttpSecurity http) {
    JWTFilter customFilter = new JWTFilter(tokenProvider);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
