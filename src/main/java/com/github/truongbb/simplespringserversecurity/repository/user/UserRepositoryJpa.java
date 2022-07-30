package com.github.truongbb.simplespringserversecurity.repository.user;

import com.github.truongbb.simplespringserversecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

}
