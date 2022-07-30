package com.github.truongbb.simplespringserversecurity.security;

import com.github.truongbb.simplespringserversecurity.entity.User;
import com.github.truongbb.simplespringserversecurity.repository.user.UserRepositoryJpa;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

  @Autowired
  UserRepositoryJpa userRepositoryJpa;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (new EmailValidator().isValid(username, null)) {
      return userRepositoryJpa.findByEmail(username)
        .map(user -> createSpringSecurityUser(username, user))
        .orElseThrow(
          () -> new UsernameNotFoundException("User with email " + username + " not found in the database")
        );
    } else return null;
  }

  private org.springframework.security.core.userdetails.User createSpringSecurityUser(String email, User user) {
    if (!user.getIsActivated()) {
      throw new UserNotActivatedException("User " + email + " was not activated");
    }
    List<GrantedAuthority> grantedAuthorities = user.getRoles()
      .stream()
      .map(
        authority ->
          new SimpleGrantedAuthority(authority.getName())
      )
      .collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
  }

}
