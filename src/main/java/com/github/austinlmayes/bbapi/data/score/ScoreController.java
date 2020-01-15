package com.github.austinlmayes.bbapi.data.score;

import com.github.austinlmayes.bbapi.Application;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the score reports API.
 *
 * @author Austin Mayes
 */
@RestController
public class ScoreController {

  /**
   * @return all score reports
   */
  @GetMapping(value = "/scores/list")
  public List<ScoreReport> all() {
    return Application.getInstance().getScoreManager().all();
  }

  /**
   * Find the top score for a specific player
   *
   * @param response used to send errors to
   * @param player to search by
   * @return the top score for the player, if one exists
   * @throws IOException if there was a file load error
   */
  @GetMapping(value = "/scores/search")
  public ScoreReport search(HttpServletResponse response,
      @RequestParam(value = "player") String player)
      throws IOException {
    ScoreReport found = Application.getInstance().getScoreManager().load(player);
    if (found == null) {
      response.sendError(404);
    }
    return found;
  }

  /**
   * Save a score report for a player.
   *
   * @param player the report is for
   * @param score that the player gor
   * @param levelsCompleted in the game session
   * @param time of the game
   * @return the result of the save
   */
  @PostMapping(value = "/scores/save")
  public String saveScore(@RequestParam("player") String player, @RequestParam("score") int score,
      @RequestParam("levels-completed") int levelsCompleted, @RequestParam("time") String time) {
    // Get previous report for player
    ScoreReport found = Application.getInstance().getScoreManager().load(player);
    if (found != null && found.getScore() > score) {
      // If they have a report, and the score is higher than the one being saved, don't overwrite.
      return "hasHigher";
    }
    // Score doesn't exist or is lower than new score
    Application.getInstance().getScoreManager()
        .save(new ScoreReport(player, score, levelsCompleted, time));
    return "newHigh";
  }
}
