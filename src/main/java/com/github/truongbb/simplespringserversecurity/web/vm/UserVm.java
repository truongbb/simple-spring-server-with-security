package com.github.truongbb.simplespringserversecurity.web.vm;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVm extends BaseVm {

  String email;
  String password;
  Boolean rememberMe;

}
