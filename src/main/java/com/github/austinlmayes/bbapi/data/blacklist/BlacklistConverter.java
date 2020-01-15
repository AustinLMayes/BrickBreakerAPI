package com.github.austinlmayes.bbapi.data.blacklist;

import com.github.austinlmayes.bbapi.POJOConverter;
import java.io.File;

/**
 * Class used to convert {@link Blacklist}s to/from files.
 *
 * @author Austin Mayes
 */
public class BlacklistConverter implements POJOConverter<Blacklist> {

  private static final String DELIMITER = ":";

  @Override
  public String subPath() {
    return "blacklists";
  }

  @Override
  public boolean isValid(File file) {
    return file.isFile() && file.getName().endsWith(".bl");
  }

  @Override
  public String fileName(Blacklist toSave) {
    return toSave.getIp().replace(".", "-") + ".bl";
  }

  @Override
  public File find(File root, String query) {
    query = query.replace(".", "-");
    for (File file : root.listFiles()) {
      if (file.isFile() && file.getName().equals(query + ".bl")) {
        return file;
      }
    }
    return null;
  }

  @Override
  public String toFileContents(Blacklist data) {
    return data.getIp() + DELIMITER
        + data.getReason();
  }

  @Override
  public Blacklist load(String fileContents) {
    String[] split = fileContents.split(DELIMITER);
    String ip = split[0];
    String reason = split[1];
    return new Blacklist(ip, reason);
  }
}
