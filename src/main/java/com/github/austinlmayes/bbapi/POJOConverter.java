package com.github.austinlmayes.bbapi;

import java.io.File;

/**
 * A class which can load and save objects of type {@link D} from/to files.
 *
 * @param <D> type of data being loaded/saved
 * @author Austin Mayes
 */
public interface POJOConverter<D> {

  /**
   * @return the subdirectory path where all data of this type should be stored.
   */
  String subPath();

  /**
   * @param toSave data to get the name from
   * @return a file-system safe file name which represents the provided data class which can be used
   * for lookup in the future
   */
  String fileName(D toSave);

  /**
   * @param file to check
   * @return if the supplied file matches the base standards of being a file which could possible
   * contain data to be loaded
   */
  boolean isValid(File file);

  /**
   * @param root directory to search for files in
   * @param query to find files by
   * @return a file (if any matched) matching the query. If no match can be found, {@code null} will
   * be returned instead.
   */
  File find(File root, String query);

  /**
   * @param data to serialize
   * @return a string representing the data which can be saved in a file and loaded at a later time
   */
  String toFileContents(D data);

  /**
   * @param fileContents to parse the data from
   * @return a fully completed object of type {@link D} which was parsed from the provided file
   * contents
   */
  D load(String fileContents);
}
