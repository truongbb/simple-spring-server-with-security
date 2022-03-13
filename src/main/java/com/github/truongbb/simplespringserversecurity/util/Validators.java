package com.github.truongbb.simplespringserversecurity.util;

import java.util.Collection;

public class Validators {

  public static boolean validObject(Object argument) {
    return argument != null;
  }

  public static boolean validCollection(Collection collection) {
    return collection != null && !collection.isEmpty();
  }

}
