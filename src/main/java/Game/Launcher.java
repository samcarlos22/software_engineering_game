package Game;

import Controller.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Game's launcher class that extends JavaFX {@code Application} class.
 * @see Application
 */
public class Launcher extends Application {

    /**
     * Starts the game inside JavaFX's {@code Platform} thread.
     * @see javafx.application.Platform
     * @param stage game's main GUI element.
     */
    @Override
    public void start(Stage stage) throws Exception {

        Controller controller = new Controller();
        controller.createView();

        Scene scene = new Scene(controller.getView());

        stage.setScene(scene);
        stage.setTitle("Game");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Launches the JavaFX {@code Platform}.
     * @see javafx.application.Platform
     * @param args start up input arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
