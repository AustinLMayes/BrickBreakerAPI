package com.github.austinlmayes.bbapi;

import com.github.austinlmayes.bbapi.data.blacklist.Blacklist;
import com.github.austinlmayes.bbapi.data.blacklist.BlacklistConverter;
import com.github.austinlmayes.bbapi.data.DataManager;
import com.github.austinlmayes.bbapi.data.FileDataManager;
import com.github.austinlmayes.bbapi.data.level.Level;
import com.github.austinlmayes.bbapi.data.level.LevelConverter;
import com.github.austinlmayes.bbapi.data.score.ScoreReport;
import com.github.austinlmayes.bbapi.data.score.ScoreReportConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class.
 *
 * @author Austin Mayes
 */
@SpringBootApplication
public class Application {

  private final DataManager<Level> levelManager = new FileDataManager<>(new LevelConverter());
  private final DataManager<ScoreReport> scoreManager = new FileDataManager<>(
      new ScoreReportConverter());
  private final DataManager<Blacklist> blacklistManager = new FileDataManager<>(
      new BlacklistConverter());
  private static Application instance;

  /**
   * Constructor, called from inside the {@link #main(String[])} method by spring.
   */
  public Application() {
    instance = this;
  }

  /**
   * Application entrypoint
   *
   * @param args passed into the application
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * @return manager used to load/save levels
   */
  public DataManager<Level> getLevelManager() {
    return levelManager;
  }

  /**
   * @return manager used to load/save score reports
   */
  public DataManager<ScoreReport> getScoreManager() {
    return scoreManager;
  }

  /**
   * @return manager used to retrieve blacklist data
   */
  public DataManager<Blacklist> getBlacklistManager() {
    return blacklistManager;
  }

  /**
   * @return instance of this object
   */
  public static Application getInstance() {
    return instance;
  }
}
