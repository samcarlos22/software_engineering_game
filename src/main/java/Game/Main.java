package Game;

import Controller.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        controller.getView().setPrefSize(600, 480);
        //controller.getView().setCenter(controller.getBoard());
        controller.getView().setAlignment(controller.getOutput(), Pos.CENTER);
        controller.getView().setMargin(controller.getOutput(), new Insets(12,12,30,12));
        //controller.getView().setBottom(controller.getOutput());


        //Set up leaderboard
        Label p1 = new Label("Player 1");
        Label p2 = new Label("Player 2");
        Label p3 = new Label("Player 3");
        Label p4 = new Label("Player 4");
        Label p5 = new Label("Player 5");
        VBox leaderboard = new VBox();
        leaderboard.getChildren().addAll(p1, p2, p3, p4, p5);
        leaderboard.setAlignment(Pos.CENTER);
        controller.getView().setAlignment(leaderboard, Pos.CENTER);
        controller.getView().setMargin(leaderboard, new Insets(12,12,12,12));
        //controller.getView().setRight(leaderboard);

        //Set up start screen
        VBox startScreen = new VBox();
        TextField name = new TextField("Player Name");
        name.setAlignment(Pos.CENTER);
        name.setMaxSize(200, 200);
        controller.getView().setAlignment(name, Pos.CENTER);
        Button start = new Button("Start");
        Button exit = new Button("Exit");
        start.setOnAction(actionEvent -> {
            start.setText("Reset");
            startScreen.getChildren().remove(name);
            controller.getView().setAlignment(start, Pos.CENTER);
            controller.getView().setCenter(controller.getBoard());
            controller.getView().setRight(leaderboard);
            controller.getView().setLeft(startScreen);
            controller.getView().setTop(new Label("Welcome to the game " + name.getText()));
            controller.getView().setBottom(controller.getOutput());
            leaderboard.getChildren().add(new Label(name.getText()));
        });
        exit.setOnAction(actionEvent -> Platform.exit());
        startScreen.getChildren().addAll(name, start, exit);
        startScreen.setAlignment(Pos.CENTER);
        controller.getView().setAlignment(startScreen, Pos.CENTER);
        controller.getView().setCenter(startScreen);

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
