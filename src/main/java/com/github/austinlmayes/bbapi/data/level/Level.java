package com.github.austinlmayes.bbapi.data.level;

/**
 * A single level of the game.
 *
 * @author Austin Mayes
 */
public class Level {

  private final String creator;
  private final String name;
  private final String desc;
  private final String contents;

  /**
   * @param creator of the level
   * @param name of the level
   * @param desc of the level
   * @param contents of the level
   */
  public Level(String creator, String name, String desc, String contents) {
    this.creator = creator;
    this.name = name;
    this.desc = desc;
    this.contents = contents;
  }

  /**
   * @return the person who created the level
   */
  public String getCreator() {
    return creator;
  }

  /**
   * @return the name of the level
   */
  public String getName() {
    return name;
  }

  /**
   * @return the description of the level
   */
  public String getDesc() {
    return desc;
  }

  /**
   * @return the level's contents, which are parsed at runtime by the Python game
   */
  public String getContents() {
    return contents;
  }
}
