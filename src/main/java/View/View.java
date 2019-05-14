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
    private GridPane board;

    /**
     * The clickable tile representing the game's board cells.
     */
    private Button cell;

    /**
     * The last cell which player has moved to.
     */
    private Button lastCell;

    /**
     * The current cell which player has clicked in.
     */
    private Button currentCell;

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
     * Sets up the game board GUI.
     * @param tempBoard the board data provided by the {@code Controller}
     * @see Controller
     */
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

    /**
     * Resizes the clickable button cells.
     */
    public void resizeCell(){
        cell.setMinSize(30, 30);
        cell.setMaxSize(30, 30);
        cell.setPrefSize(30, 30);
    }

    /**
     * Change the clickable button cells color to RED.
     * @param cell which cell to mark.
     */
    public void mark(Button cell){
        cell.setStyle("-fx-base: #ee2211;");
    }

    /**
     * Change the clickable button cells color to WHITE.
     * @param cell which cell to unmark.
     */
    public void unmark(Button cell){
        cell.setStyle("-fx-base: #ffffff;");
    }

    /**
     * Changes the clickable button exibition text from 1 to [1].
     * This status changes the board's moviment dinamic.
     */
    public void blockCell(){
        cell.setText("[" + cell.getText() + "]");
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
            controller.createBoard(10,10);
            setUpBoard(controller.getBoard());
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

