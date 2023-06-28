package starterCode.view;

import starterCode.controller.SudokuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * Represents a simple Java Bujo GUI starterCode.view.
 */
public class BujoViewImpl implements BujoView {
  private final FXMLLoader loader;

  /**
   * constructor for the starterCode.view.BujoViewImpl class
   *
   * @param controller the starterCode.controller used for this Java Bujo
   */
  public BujoViewImpl(SudokuController controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("sudokuboard.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Loads a scene from a Java Bujo GUI layout.
   *
   * @return the layout
   */
  @Override
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
