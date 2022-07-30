package com.github.truongbb.simplespringserversecurity.service.user;

import com.github.truongbb.simplespringserversecurity.entity.Role;
import com.github.truongbb.simplespringserversecurity.entity.User;
import com.github.truongbb.simplespringserversecurity.repository.user.UserRepository;
import com.github.truongbb.simplespringserversecurity.repository.user.UserRepositoryJpa;
import com.github.truongbb.simplespringserversecurity.web.vm.UserVm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

  UserRepository userRepository;

  UserRepositoryJpa userRepositoryJpa;

  @Override
  public List<User> search(UserVm userVm) {
    log.debug("search service entered...");
    return userRepository.search(userVm);
  }

  @Override
  public User getUserInfo() {

    User userEntity;

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails principal = (UserDetails) authentication.getPrincipal();
    Set<Role> roles = principal.getAuthorities()
      .stream()
      .map(r -> new Role(null, r.getAuthority()))
      .collect(Collectors.toSet());
    String email = principal.getUsername();
    Optional<User> userOptional = userRepositoryJpa.findByEmail(email);
    if (userOptional.isPresent()) {
      userEntity = userOptional.get();
      userEntity.setPassword(null);
      userEntity.setRoles(roles);
    } else {
      throw new UsernameNotFoundException("Email " + email + " not found");
    }
    return userEntity;
  }

}
