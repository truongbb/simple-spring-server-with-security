package com.github.truongbb.simplespringserversecurity.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Slf4j
public class SqlBuilder {

  public static final String SQL_MODULE_USER = "user";

  public static String getSqlFromFile(String module, String sqlFileName) {
    File folder;
    try {
      folder = new ClassPathResource("sql" + File.separator + module + File.separator + sqlFileName + ".sql").getFile();

      if (folder.isFile()) {
        String sql = new String(Files.readAllBytes(Paths.get(folder.getAbsolutePath())));
        return sql;
      }
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    return null;
  }

}
