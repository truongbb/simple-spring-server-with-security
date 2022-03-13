package com.github.truongbb.simplespringserversecurity.repository.user;

import com.github.truongbb.simplespringserversecurity.dto.UserDto;
import com.github.truongbb.simplespringserversecurity.entity.User;
import com.github.truongbb.simplespringserversecurity.web.vm.UserVm;

import java.util.List;

public interface UserRepository {

    /**
     * @param searchField is one of "email", "username", "phone"
     * @param value       is search value for corresponding search field
     * @return List<UserDto>
     */
    List<UserDto> findByUsernameOrEmailOrPhone(String searchField, String value);

    boolean updateUserEntity(User usersEntity);

    List<User> search(UserVm userVm);

}
