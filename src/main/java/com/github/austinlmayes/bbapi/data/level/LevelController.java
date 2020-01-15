package com.github.austinlmayes.bbapi.data.level;

import com.github.austinlmayes.bbapi.Application;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the levels API.
 *
 * @author Austin Mayes
 */
@RestController
public class LevelController {

  /**
   * @return all level groups
   */
  @GetMapping(value = "/levels/groups")
  public List<String> groups() {
    return Application.getInstance().getLevelManager().all().stream()
        .filter(l -> l.getName().contains("-1")).map(l -> l.getName().replace("-1", ""))
        .collect(Collectors.toList());
  }

  /**
   * Get a level by name.
   *
   * @param response used to set error code
   * @param name of the requested level
   * @return the level which matches the query, if present
   * @throws IOException if a file laod error occured
   */
  @GetMapping(value = "/levels/search")
  public Level getLevel(HttpServletResponse response, @RequestParam(value = "name") String name)
      throws IOException {
    Level found = Application.getInstance().getLevelManager().load(name);
    if (found == null) {
      response.sendError(404);
      System.out.println(name);
    }
    return found;
  }

  /**
   * Save a level.
   *
   * @param creator of the level
   * @param name of the level
   * @param desc of the level
   * @param contents of the level
   */
  @PostMapping(value = "/levels/save")
  public void saveLevel(@RequestParam("creator") String creator, @RequestParam("name") String name,
      @RequestParam("desc") String desc, @RequestParam("contents") String contents) {
    Application.getInstance().getLevelManager().save(new Level(creator, name, desc, contents));
  }
}
