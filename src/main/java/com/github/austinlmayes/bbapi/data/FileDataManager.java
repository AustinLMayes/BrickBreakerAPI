package com.github.austinlmayes.bbapi.data;

import com.github.austinlmayes.bbapi.POJOConverter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple data manager implementation which sotores data in files.
 *
 * @param <D> type of data being handled
 * @author Austin Mayes
 */
public class FileDataManager<D> implements DataManager<D> {

  private final File root;
  private final POJOConverter<D> converter;

  /**
   * Constructor.
   *
   * If the data container does not exist on the system, it will be created when this method is
   * executed.
   *
   * @param converter used to convert data from/to files
   */
  public FileDataManager(POJOConverter<D> converter) {
    this.converter = converter;
    root = new File(System.getProperty("user.dir") + File.separator + "data", converter.subPath());
    if (!root.exists()) {
      root.mkdirs();
    }
  }

  /**
   * Attempt to find data in the {@link POJOConverter#subPath()} for this object using the {@link
   * POJOConverter#find(File, String)} find method. If a result is found, it is loaded using {@link
   * POJOConverter#load(String)}. If a result is not found, {@code null} will be returned instead.
   *
   * @param query used to search for data
   * @return the loaded data, if present
   */
  @Override
  public D load(String query) {
    File found = converter.find(root, query);
    if (found == null) {
      return null;
    }
    try {
      return converter.load(new String(Files.readAllBytes(found.toPath())));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Load all files in the {@link POJOConverter#subPath()} for this object. Only files which match
   * the {@link POJOConverter#isValid(File)} standards will be loaded.
   *
   * @return all files
   */
  @Override
  public List<D> all() {
    List<D> res = new ArrayList<>();
    for (File file : root.listFiles()) {
      if (converter.isValid(file)) {
        try {
          res.add(converter.load(new String(Files.readAllBytes(file.toPath()))));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return res;
  }

  /**
   * Create and save a file containing the contents of the supplied data. The file is named using
   * the {@link POJOConverter#fileName(Object)} helper and contents are supplied by {@link
   * POJOConverter#toFileContents(Object)}.
   *
   * @param data to save
   */
  @Override
  public void save(D data) {
    String contents = converter.toFileContents(data);
    String name = converter.fileName(data);
    File where = new File(root, name);
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(where), StandardCharsets.UTF_8))) {
      writer.write(contents);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
