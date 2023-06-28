package starterCode;

import starterCode.controller.SudokuControllerImpl;
import javafx.application.Application;
import javafx.stage.Stage;
import starterCode.view.BujoViewImpl;

public class Driver extends Application {

    /**starts the GUI for the Java Bujo
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        SudokuControllerImpl bci = new SudokuControllerImpl(stage);
        BujoViewImpl bvi = new BujoViewImpl(bci);
        try {
            stage.setTitle("BuJo");
            stage.setScene(bvi.load());
            bci.run();
            stage.show();
        } catch (IllegalStateException ise) {
            System.err.println("Unable to load GUI.");
        }
    }

    /**
     * Entry point for a Java Bujo
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
