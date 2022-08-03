package com.github.truongbb.simplespringserversecurity.web.rest;

import com.github.truongbb.simplespringserversecurity.security.jwt.JWTFilter;
import com.github.truongbb.simplespringserversecurity.security.jwt.TokenProvider;
import com.github.truongbb.simplespringserversecurity.web.vm.UserVm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("${spring.data.rest.base-path}")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationResource {

  AuthenticationManagerBuilder authenticationManagerBuilder;

  TokenProvider tokenProvider;

  @PostMapping("/authenticate")
  public ResponseEntity<Object> authorize(@Valid @RequestBody UserVm userVm) {
    log.trace("REST request to authenticate user: {}", userVm);
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userVm.getEmail(), userVm.getPassword());
    Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = tokenProvider.createToken(authentication, userVm.getRememberMe());
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, String.format("Bearer %s", jwt));
    return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
  }

}
