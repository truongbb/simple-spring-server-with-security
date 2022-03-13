package com.github.truongbb.simplespringserversecurity.web.vm;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PROTECTED)
public class BaseVm {

  Integer skip = 0;
  Integer take = 10;

}
