package Game;

import Controller.Controller;
import View.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

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

    public static void main(String[] args) {
        launch(args);
    }
}
