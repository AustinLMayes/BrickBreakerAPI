package com.github.austinlmayes.bbapi.data;

import java.util.List;

/**
 * Manager which load/saves data of type {@link D}.
 *
 * @param <D> type of data being handled
 * @author Austin Mayes
 */
public interface DataManager<D> {

  /**
   * Attempt to load the data represented by the query.
   *
   * @param query to search by
   * @return the loaded data, if present
   */
  D load(String query);

  /**
   * @return all stored data of type {@link D}
   */
  List<D> all();

  /**
   * Save a single data object.
   *
   * @param data to save
   */
  void save(D data);
}
