package com.github.truongbb.simplespringserversecurity.security;

import org.springframework.security.core.AuthenticationException;

public class UserNotActivatedException extends AuthenticationException {

  public UserNotActivatedException(String msg) {
    super(msg);
  }

}
