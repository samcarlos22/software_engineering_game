package View;

import Controller.Controller;
import Model.Board;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class View extends BorderPane{

    private Controller controller;

    private GridPane board;
    private Button cell;
    private Button lastCell;
    private Button currentCell;
    private Button startButton;
    private Button restartButton;
    private Button exitButton;
    private TextField nameField;
    private VBox startMenu;

    public View(Controller controller) {
        this.controller = controller;

        setPrefSize(600, 480);

        setUpStartMenu();

    }

    public void setUpStartMenu() {
        nameField = new TextField("Please, insert your name");
        nameField.setAlignment(Pos.CENTER);
        nameField.setMaxSize(200, 200);

        startButton = new Button("Start Game");
        startButton.setOnAction(startGameEvent());

        exitButton = new Button("Exit Game");
        exitButton.setOnAction(exitGameEvent());

        startMenu = new VBox();
        startMenu.getChildren().addAll(nameField, startButton, exitButton);
        startMenu.setAlignment(Pos.CENTER);

        setAlignment(nameField, Pos.CENTER);
        setAlignment(startMenu, Pos.CENTER);

        setCenter(startMenu);
    }

    public void setUpBoard(){
        board = new GridPane();
        String tempBoard[][] = controller.getBoard();
        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard[0].length; y++) {
                String[] cellAttributes = tempBoard[x][y].split(",");

                cell = new Button();

                cell.setText(cellAttributes[0]);
                cell.setId(tempBoard[x][y]);

                resizeCell();
                unmarkCell();

                if (Double.parseDouble(cellAttributes[3]) <= 0.20) {
                    blockCell();
                }

                if (x == 0 && y == 0) {
                    markCell();
                    lastCell = cell;
                }

                if(x == tempBoard.length - 1 && y == tempBoard[0].length - 1){
                    cell.setId("100");
                    cell.setText("*");
                    //cell.setOnAction(addWinEvent());
                }

                board.add(cell, x, y, 1, 1);
            }
        }
    }

    public void resizeCell(){
        cell.setMinSize(30, 30);
        cell.setMaxSize(30, 30);
        cell.setPrefSize(30, 30);
    }

    public void markCell(){
        cell.setStyle("-fx-base: #ee2211;");
    }

    public void unmarkCell(){
        cell.setStyle("-fx-base: #ffffff;");
    }

    public void blockCell(){
        cell.setText("[" + cell.getText() + "]");
    }

    public EventHandler<ActionEvent> startGameEvent(){
        return startGame -> {
            restartButton = new Button("Restart Game");
            restartButton.setOnAction(restartGameEvent());

            startMenu.getChildren().removeAll(nameField, startButton, exitButton);
            startMenu.getChildren().addAll(restartButton, exitButton);

            setAlignment(startMenu, Pos.CENTER);

            setUpBoard();

            setCenter(board);
            setLeft(startMenu);

        };
    }

    public EventHandler<ActionEvent> restartGameEvent(){
        return restartGame -> {
            //controller.createBoard(10,10);
            //controller.getBoard().setAlignment(Pos.CENTER);
            //controller.getView().setCenter(controller.getBoard());
        };
    }

    public EventHandler<ActionEvent> exitGameEvent(){
        return exitGame -> Platform.exit();
    }

}

    /*
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



    public EventHandler<ActionEvent> startGameEvent(){
        return startGame -> {
            restartButton.setOnAction(restartGameEvent());
            startMenu.getChildren().removeAll(nameField, startButton, exitButton);
            startMenu.getChildren().addAll(restartButton, exitButton);

            view.setAlignment(startMenu, Pos.CENTER);

            controller.getPlayer().setName(nameField.getText());

            view.setCenter(controller.getBoard());
            view.setRight(controller.getLeaderboard());
            view.setLeft(startMenu);
            view.setTop(new Label("Welcome to the game " + controller.getPlayer().getName()));
            //view.setBottom(controller.getOutput());

            //controller.getLeaderboard().getChildren().add(new Label(controller.getPlayer().getName()));
        };
    }




    */

