package com.github.truongbb.simplespringserversecurity.util;

public class StringUtil {

  public static boolean isNotNullAndEmptyString(String srcString) {
    return !(srcString == null || "".equals(srcString.trim()));
  }

}
