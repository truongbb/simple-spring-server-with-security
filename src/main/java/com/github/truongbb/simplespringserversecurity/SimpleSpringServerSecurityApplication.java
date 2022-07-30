package com.github.truongbb.simplespringserversecurity;

import com.github.truongbb.simplespringserversecurity.entity.Role;
import com.github.truongbb.simplespringserversecurity.entity.User;
import com.github.truongbb.simplespringserversecurity.repository.role.RoleRepositoryJpa;
import com.github.truongbb.simplespringserversecurity.repository.user.UserRepositoryJpa;
import com.github.truongbb.simplespringserversecurity.security.AuthoritiesConstants;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleSpringServerSecurityApplication implements CommandLineRunner {

  UserRepositoryJpa userRepositoryJpa;
  RoleRepositoryJpa roleRepositoryJpa;

  PasswordEncoder bCryptPasswordEncoder;

  public static void main(String[] args) {
    SpringApplication.run(SimpleSpringServerSecurityApplication.class, args);
  }

  /**
   * automatic create default admin user in the first run
   */
  @Override
  public void run(String... args) {
    List<Role> roles = roleRepositoryJpa.findAll();

    if (CollectionUtils.isEmpty(roles)) {
      List<Role> roleList = Arrays.asList(new Role(null, AuthoritiesConstants.ROLE_ADMIN), new Role(null, AuthoritiesConstants.ROLE_USER));
      roleRepositoryJpa.saveAll(roleList);
    }

    Optional<User> optionalUser = userRepositoryJpa.findByEmail("admin@gmail.com");
    if (!optionalUser.isPresent()) {

      List<Role> adminRole = Arrays.asList(roleRepositoryJpa.findByName(AuthoritiesConstants.ROLE_ADMIN));
      HashSet<Role> adminRoleSet = new HashSet<>(adminRole);

      User user = new User();
      user.setEmail("admin@gmail.com");
      user.setPassword(bCryptPasswordEncoder.encode("admin"));
      user.setIsActivated(true);
      user.setRoles(adminRoleSet);
      userRepositoryJpa.save(user);
    }
  }
}
