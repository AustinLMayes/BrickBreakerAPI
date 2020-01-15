package com.github.austinlmayes.bbapi.data.score;

import com.github.austinlmayes.bbapi.POJOConverter;
import java.io.File;

/**
 * Class used to convert {@link ScoreReport}s to/from files.
 *
 * @author Austin Mayes
 */
public class ScoreReportConverter implements POJOConverter<ScoreReport> {

  private static final String DELIMITER = "==/:/==";

  @Override
  public String subPath() {
    return "score-reports";
  }

  @Override
  public boolean isValid(File file) {
    return file.isFile() && file.getName().endsWith(".scrp");
  }

  @Override
  public String fileName(ScoreReport toSave) {
    return toSave.getPlayer() + ".scrp";
  }

  @Override
  public File find(File root, String query) {
    for (File file : root.listFiles()) {
      if (file.isFile() && file.getName().equals(query + ".lvl")) {
        return file;
      }
    }
    return null;
  }

  @Override
  public String toFileContents(ScoreReport data) {
    return data.getPlayer() + DELIMITER + data.getScore() + DELIMITER + data.getLevelsCompleted()
        + DELIMITER + data.getTime();
  }

  @Override
  public ScoreReport load(String fileContents) {
    String[] split = fileContents.split(DELIMITER);
    String player = split[0];
    int score = Integer.parseInt(split[1]);
    int levelsCompleted = Integer.parseInt(split[2]);
    String time = split[3];
    return new ScoreReport(player, score, levelsCompleted, time);
  }
}
