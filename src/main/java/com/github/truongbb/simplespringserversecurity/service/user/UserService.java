package com.github.truongbb.simplespringserversecurity.service.user;

import com.github.truongbb.simplespringserversecurity.entity.User;
import com.github.truongbb.simplespringserversecurity.web.vm.UserVm;

import java.util.List;

public interface UserService {

    List<User> search(UserVm userVm);

    User getUserInfo();

}
