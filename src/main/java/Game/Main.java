package Game;

import Controller.Controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Set up controller
        Controller controller = new Controller();

        //Set up board
        controller.createBoard();
        controller.fillBoard(10,10);

        //Set up output text label
        controller.createOutput();
        controller.getOutput().setTextAlignment(TextAlignment.CENTER);
        controller.getOutput().setTextFill(Paint.valueOf("blue"));

        //Set up view
        controller.createView();
        controller.getView().setPrefSize(400, 400);
        controller.getView().setCenter(controller.getBoard());
        controller.getView().setAlignment(controller.getOutput(), Pos.CENTER);
        controller.getView().setMargin(controller.getOutput(), new Insets(12,12,30,12));
        controller.getView().setBottom(controller.getOutput());

        //Set up scene
        controller.createScene(controller.getView());

        //Set up stage
        stage.setScene(controller.getScene());
        stage.setTitle("Game");
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
