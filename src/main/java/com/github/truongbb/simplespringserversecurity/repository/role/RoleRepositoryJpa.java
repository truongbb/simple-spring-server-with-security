package com.github.truongbb.simplespringserversecurity.repository.role;

import com.github.truongbb.simplespringserversecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoryJpa extends JpaRepository<Role, Long> {

  Role findByName(String roleName);

}
