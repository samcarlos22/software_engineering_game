package View;

import Controller.Controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import org.tinylog.Logger;

import java.util.List;

/**
 * Class representing the game's GUI.
 */
public class View extends BorderPane{
    /**
     * The game's controller.
     */
    private Controller controller;

    /**
     * The matrix pane representing the game's board.
     */
    private GridPane grid;

    /**
     * The button that starts the game.
     */
    private Button startButton;

    /**
     * The button that restarts the game.
     */
    private Button restartButton;

    /**
     * The button that exits the game.
     */
    private Button exitButton;

    /**
     * The text box that registers the player's name.
     */
    private TextField nameField;

    /**
     * The array representing the start menu buttons.
     */
    private VBox startMenu;

    /**
     * The label that shows feedback messages to the player.
     */
    private Label outputMessage;

    /**
     * The label that show a welcome message to the player.
     */
    private Label welcomeMessage;

    /**
     * Creates a {@code View} object containing the game's GUI.
     * @param controller the {@code Controller} instance reference.
     * @see Controller
     */
    public View(Controller controller) {

        this.controller = controller;

        setPrefSize(600, 480);

        setUpStartMenu();

    }

    /**
     * Sets up the start menu gui.
     */
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

    /**
     * Collects board's data from {@code Controller} and sets up a grid view.
     * @see Controller
     */
    public void setUpBoard(){
        grid = new GridPane();
        for (int x = 0; x < controller.getBoard().length; x++) {
            for (int y = 0; y < controller.getBoard()[0].length; y++) {
                Cell cell = new Cell(x, y, controller.getBlockMap()[x][y]);

                cell.setText(Integer.toString(controller.getBoard()[x][y]));
                cell.setId(cell.getText());

                cell.setMinSize(30, 30);
                cell.setMaxSize(30, 30);
                cell.setPrefSize(30, 30);

                if (cell.isBlocked()) {
                    cell.setText("[" + cell.getText() + "]");
                }

                update(controller.getCurrentState());

                cell.setOnAction(addMoveEvent());

                grid.add(cell, x, y, 1, 1);
            }
        }

        grid.setAlignment(Pos.CENTER);
        setCenter(grid);
    }

    /**
     * Updates {@GridPane}'s view.
     * @param state the current {@code Board}'s state.
     * @see Model.Board
     * @see GridPane
     */
    public void update(int[][] state){

        ObservableList<Node> cells = grid.getChildren();
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                for (Node cell : cells) {
                    if (GridPane.getRowIndex(cell) == x && GridPane.getColumnIndex(cell) == y) {
                        if (state[x][y] == 0){
                            cell.setStyle("-fx-base: #ffffff;");
                        }
                        else {
                            cell.setStyle("-fx-base: #ee2211;");
                        }
                        //break;
                    }
                }
    }

    /**
     * Event that starts the game and generates a new board
     * with data provided by the {@code Controller}.
     * @return an action event (start game) containing a lambda expression.
     * @see Controller
     * @see EventHandler
     * @see ActionEvent
     */
    private EventHandler<ActionEvent> startGameEvent(){
        return startGame -> {
            controller.createBoard();
            controller.createPlayer(nameField.getText());

            restartButton = new Button("Restart Game");
            restartButton.setOnAction(restartGameEvent());

            startMenu.getChildren().removeAll(nameField, startButton, exitButton);
            startMenu.getChildren().addAll(restartButton, exitButton);

            setAlignment(startMenu, Pos.CENTER);

            setUpBoard();

            outputMessage = new Label();
            outputMessage.setAlignment(Pos.CENTER);
            outputMessage.setTextAlignment(TextAlignment.CENTER);
            outputMessage.setTextFill(Paint.valueOf("blue"));

            welcomeMessage = new Label("Welcome, " + controller.getPlayer().getName());
            welcomeMessage.setAlignment(Pos.CENTER);
            welcomeMessage.setTextAlignment(TextAlignment.CENTER);
            welcomeMessage.setTextFill(Paint.valueOf("blue"));

            setAlignment(grid, Pos.CENTER);
            //setAlignment(controller.getLeaderboard(), Pos.CENTER);
            setAlignment(outputMessage, Pos.CENTER);
            setMargin(outputMessage, new Insets(12,12,30,12));
            setAlignment(welcomeMessage, Pos.CENTER);
            setMargin(welcomeMessage, new Insets(30,12,12,12));

            setTop(welcomeMessage);
            setCenter(grid);
            setLeft(startMenu);
            setBottom(outputMessage);
        };
    }

    /**
     * Event that restarts the game and generates a new board
     * with data provided by the {@code Controller}.
     * @return an action event (restart game)
     * containing a lambda expression.
     * @see Controller
     * @see EventHandler
     * @see ActionEvent
     */
    private EventHandler<ActionEvent> restartGameEvent(){
        return restartGame -> {
            setUpBoard();
        };
    }

    /**
     * Event that finishes and closes the game.
     * @return an action event (restart game)
     * containing a lambda expression.
     * @see EventHandler
     * @see ActionEvent
     */
    private EventHandler<ActionEvent> exitGameEvent(){
        return event -> Platform.exit();
    }

    /**
     * Event that changes board's cell tiles color according
     * to player's movements. The {@code Controller}
     * verifies if it's a valid movement.
     * @return an action event (move) containing a lambda expression.
     * @see Controller
     * @see EventHandler
     * @see ActionEvent
     */
    private EventHandler<ActionEvent> addMoveEvent() {
        return event -> {
            //try {
                Cell clickedCell = (Cell) event.getSource();
                Boolean moved = controller.move(Integer.parseInt(clickedCell.getId()), clickedCell.getX(), clickedCell.getY());
                if (moved){
                    update(controller.getCurrentState());
                    outputMessage.setText("");
                    if (controller.isGoal()) {
                        outputMessage.setText("Congratulations!! You won.");
                    }
                } else
                    outputMessage.setText("Invalid move");
            //} catch (Exception e){
            //    Logger.error(e.getMessage());
            //}
        };
    }

}

