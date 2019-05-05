package Game;

import Controller.Controller;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller();

        controller.createView();

        controller.createBoard();
        controller.fillBoard(10,10);

        BorderPane pane = new BorderPane(controller.getBoard());
        pane.setPrefSize(400, 400);
        pane.setCenter(controller.getBoard());

        controller.createScene(pane);

        stage.setScene(controller.getScene());
        stage.setTitle("Game");
        stage.setResizable(false);

        controller.showView(stage);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
