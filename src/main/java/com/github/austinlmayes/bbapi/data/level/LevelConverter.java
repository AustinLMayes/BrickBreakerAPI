package com.github.austinlmayes.bbapi.data.level;

import com.github.austinlmayes.bbapi.POJOConverter;
import java.io.File;

/**
 * Class used to convert {@link Level}s to/from files.
 *
 * @author Austin Mayes
 */
public class LevelConverter implements POJOConverter<Level> {

  private static final String DELIMITER = "==/:/==";

  @Override
  public String subPath() {
    return "levels";
  }

  @Override
  public boolean isValid(File file) {
    return file.isFile() && file.getName().endsWith(".lvl");
  }

  @Override
  public String fileName(Level toSave) {
    return toSave.getName() + ".lvl";
  }

  @Override
  public File find(File root, String query) {
    for (File file : root.listFiles()) {
      if (file.isFile() && file.getName().equalsIgnoreCase(query + ".lvl")) {
        return file;
      }
    }
    return null;
  }

  @Override
  public String toFileContents(Level data) {
    return data.getCreator() + DELIMITER
        + data.getName() + DELIMITER
        + data.getDesc() + DELIMITER
        + data.getContents();
  }

  @Override
  public Level load(String fileContents) {
    String[] split = fileContents.split(DELIMITER);
    String creator = split[0];
    String name = split[1];
    String desc = split[2];
    String contents = split[3];
    return new Level(creator, name, desc, contents);
  }
}
