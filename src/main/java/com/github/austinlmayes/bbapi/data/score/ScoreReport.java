package com.github.austinlmayes.bbapi.data.score;

/**
 * A report generated at the end of a game session for a specific player.
 *
 * @author Austin Mayes
 */
public class ScoreReport {

  private final String player;
  private final int score;
  private final int levelsCompleted;
  private final String time;

  /**
   * @param player the report is for
   * @param score that the player gor
   * @param levelsCompleted in the game session
   * @param time of the game
   */
  ScoreReport(String player, int score, int levelsCompleted, String time) {
    this.player = player;
    this.score = score;
    this.levelsCompleted = levelsCompleted;
    this.time = time;
  }

  /**
   * @return the player this report is for
   */
  public String getPlayer() {
    return player;
  }

  /**
   * @return score the player got in the game
   */
  public int getScore() {
    return score;
  }

  /**
   * @return number of levels the player completed
   */
  public int getLevelsCompleted() {
    return levelsCompleted;
  }

  /**
   * @return the time it took the player to be knocked out
   */
  public String getTime() {
    return time;
  }
}
