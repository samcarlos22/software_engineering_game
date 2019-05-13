package View;

import Controller.Controller;

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
    private Label outputMessage;
    private Label welcomeMessage;

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

    public void setUpBoard(String[][] tempBoard){
        board = new GridPane();
        for (int x = 0; x < tempBoard.length; x++) {
            for (int y = 0; y < tempBoard[0].length; y++) {
                String[] cellAttributes = tempBoard[x][y].split(",");

                cell = new Button();

                cell.setText(cellAttributes[0]);
                cell.setId(tempBoard[x][y]);

                resizeCell();
                unmark(cell);

                if (Double.parseDouble(cellAttributes[3]) <= 0.20) {
                    blockCell();
                }

                if (x == 0 && y == 0) {
                    mark(cell);
                    lastCell = cell;
                }

                if(x == tempBoard.length - 1 && y == tempBoard[0].length - 1){
                    //cell.setId("100");
                    cell.setText("*");
                }

                cell.setOnAction(addMoveEvent());

                board.add(cell, x, y, 1, 1);
            }
        }

        board.setAlignment(Pos.CENTER);
        setCenter(board);
    }

    public void resizeCell(){
        cell.setMinSize(30, 30);
        cell.setMaxSize(30, 30);
        cell.setPrefSize(30, 30);
    }

    public void mark(Button cell){
        cell.setStyle("-fx-base: #ee2211;");
    }

    public void unmark(Button cell){
        cell.setStyle("-fx-base: #ffffff;");
    }

    public void blockCell(){
        cell.setText("[" + cell.getText() + "]");
    }

    private EventHandler<ActionEvent> startGameEvent(){
        return startGame -> {
            controller.createBoard(10, 10);
            controller.createPlayer(nameField.getText());

            restartButton = new Button("Restart Game");
            restartButton.setOnAction(restartGameEvent());

            startMenu.getChildren().removeAll(nameField, startButton, exitButton);
            startMenu.getChildren().addAll(restartButton, exitButton);

            setAlignment(startMenu, Pos.CENTER);

            setUpBoard(controller.getBoard());

            outputMessage = new Label();
            outputMessage.setAlignment(Pos.CENTER);
            outputMessage.setTextAlignment(TextAlignment.CENTER);
            outputMessage.setTextFill(Paint.valueOf("blue"));

            welcomeMessage = new Label("Welcome, " + controller.getPlayer().getName());
            welcomeMessage.setAlignment(Pos.CENTER);
            welcomeMessage.setTextAlignment(TextAlignment.CENTER);
            welcomeMessage.setTextFill(Paint.valueOf("blue"));

            setAlignment(board, Pos.CENTER);
            //setAlignment(controller.getLeaderboard(), Pos.CENTER);
            setAlignment(outputMessage, Pos.CENTER);
            setMargin(outputMessage, new Insets(12,12,30,12));
            setAlignment(welcomeMessage, Pos.CENTER);
            setMargin(welcomeMessage, new Insets(30,12,12,12));

            setTop(welcomeMessage);
            setCenter(board);
            setLeft(startMenu);
            setBottom(outputMessage);
        };
    }

    private EventHandler<ActionEvent> restartGameEvent(){
        return restartGame -> {
            controller.createBoard(10,10);
            setUpBoard(controller.getBoard());
        };
    }

    private EventHandler<ActionEvent> exitGameEvent(){
        return exitGame -> Platform.exit();
    }

    private EventHandler<ActionEvent> addMoveEvent() {
        return event -> {
            currentCell = (Button) event.getSource();
            if (controller.canMove(lastCell.getId(), currentCell.getId())) {
                unmark(lastCell);
                lastCell = currentCell;
                mark(lastCell);
                outputMessage.setText("");
                if(controller.isGoal(lastCell.getText())){
                    outputMessage.setText("Congratulations!! You won.");
                }
            }
            else
                outputMessage.setText("Invalid move");
        };
    }

}

