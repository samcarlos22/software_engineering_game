package Game;

import Controller.Controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;



public class Main extends Application {

    static Controller controller;
    static Button startButton;
    static Button restartButton;
    static Button exitButton;
    static TextField nameField;
    static VBox startMenu;

    @Override
    public void start(Stage stage) throws Exception {
        startButton = new Button("Start");
        restartButton = new Button("Reset");
        exitButton = new Button("Exit");
        nameField = new TextField("Player Name");
        startMenu = new VBox();

        //Set up controller
        controller = new Controller();

        //Set up board
        controller.createBoard();
        controller.fillBoard(10,10);
        controller.getBoard().setAlignment(Pos.CENTER);

        //Set up output text label
        controller.createOutput();
        controller.getOutput().setTextAlignment(TextAlignment.CENTER);
        controller.getOutput().setTextFill(Paint.valueOf("blue"));

        //Set up view
        controller.createView();
        controller.getView().setPrefSize(600, 480);

        //Set up new player
        controller.createPlayer();
        controller.getPlayer().setAlignment(Pos.CENTER);

        //Set up leaderboard
        controller.createLeaderboard();
        controller.getLeaderboard().setAlignment(Pos.CENTER);

        setUpStartMenu();

        //Set up view alignment and margin
        controller.getView().setAlignment(controller.getBoard(), Pos.CENTER);
        controller.getView().setAlignment(controller.getLeaderboard(), Pos.CENTER);
        controller.getView().setAlignment(controller.getOutput(), Pos.CENTER);
        controller.getView().setMargin(controller.getOutput(), new Insets(12,12,30,12));

        //Set up scene
        controller.createScene(controller.getView());

        //Set up stage
        stage.setScene(controller.getScene());
        stage.setTitle("Game");
        stage.setResizable(false);

        stage.show();
    }

    public void setUpStartMenu(){
        nameField.setAlignment(Pos.CENTER);
        nameField.setMaxSize(200, 200);

        startButton.setOnAction(startGameEvent());
        exitButton.setOnAction(exitGameEvent());

        startMenu.getChildren().addAll(nameField, startButton, exitButton);
        startMenu.setAlignment(Pos.CENTER);

        controller.getView().setAlignment(nameField, Pos.CENTER);
        controller.getView().setAlignment(startMenu, Pos.CENTER);

        controller.getView().setCenter(startMenu);
    }

    public EventHandler<ActionEvent> startGameEvent(){
        return startGame -> {
            restartButton.setOnAction(restartGameEvent());
            startMenu.getChildren().removeAll(nameField, startButton, exitButton);
            startMenu.getChildren().addAll(restartButton, exitButton);

            controller.getView().setAlignment(startMenu, Pos.CENTER);

            controller.getPlayer().setName(nameField.getText());

            controller.getView().setCenter(controller.getBoard());
            controller.getView().setRight(controller.getLeaderboard());
            controller.getView().setLeft(startMenu);
            controller.getView().setTop(new Label("Welcome to the game " + controller.getPlayer().getName()));
            controller.getView().setBottom(controller.getOutput());

            //controller.getLeaderboard().getChildren().add(new Label(controller.getPlayer().getName()));
        };
    }

    public EventHandler<ActionEvent> restartGameEvent(){
        return restartGame -> {
            controller.createBoard();
            controller.fillBoard(10,10);
            controller.getBoard().setAlignment(Pos.CENTER);
            controller.getView().setCenter(controller.getBoard());
        };
    }

    public EventHandler<ActionEvent> exitGameEvent(){
        return exitGame -> Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
