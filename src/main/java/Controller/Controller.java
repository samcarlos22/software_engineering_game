package Controller;

import Model.Board;
import Model.Cell;
import View.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Random;


public class Controller {
    private View view;
    private Board board;
    private Cell lastCell;
    private Cell currentCell;
    private Scene scene;
    private Label output;

    public void createScene(Parent sceneRoot){
        scene = new Scene(sceneRoot);
    }

    public Scene getScene(){
        return scene;
    }

    public void addToScene(Parent sceneRoot){
        scene.setRoot(sceneRoot);
    }

    public void createView() {
        view = new View();
    }

    public BorderPane getView() {
        return view;
    }

    public void createBoard(){
        board = new Board();
    }

    public Board getBoard(){
        return board;
    }

    public void createOutput(){
        output = new Label();
    }

    public Label getOutput(){
        return output;
    }

    public void fillBoard(Integer rowsNumber, Integer columnsNumber) {
        Random random = new Random();
        for (int x = 0; x < rowsNumber; x++) {
            for (int y = 0; y < columnsNumber; y++) {
                float blockChance = random.nextFloat();
                Boolean isBlocked = false;
                if (blockChance <= 0.20) {
                    isBlocked = true;
                }

                Cell c = new Cell(x, y, isBlocked, random.nextInt(4) + 1);

                //Starting point of the board
                if (x == 0 && y == 0) {
                    lastCell = c;
                    lastCell.mark();
                }

                if(x == rowsNumber - 1 && y == columnsNumber - 1){
                    c.setId("100");
                    c.setText("*");
                    c.setEvent(addWinEvent());
                }
                else
                    c.setEvent(addMoveEvent());

                board.add(c, x, y);
            }
        }
    }

    private EventHandler<ActionEvent> addMoveEvent() {
        return event -> {
            currentCell = (Cell) event.getSource();
            if (canMoveToCell()) {
                lastCell.unmark();
                lastCell = currentCell;
                lastCell.mark();
            }
        };
    }

    private EventHandler<ActionEvent> addWinEvent(){
        return event -> {
            currentCell = (Cell) event.getSource();
            if (canMoveToCell()) {
                lastCell.unmark();
                lastCell = currentCell;
                lastCell.mark();
                output.setText("Congratulations!! You won.");
            }
        };
    }

    private Boolean canMoveToCell() {
        if (!lastCell.isBlocked()) {
            if (currentCell.getCoordX() == lastCell.getCoordX() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY()
                    || currentCell.getCoordX() == lastCell.getCoordX() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY()
                    || currentCell.getCoordY() == lastCell.getCoordY() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX()
                    || currentCell.getCoordY() == lastCell.getCoordY() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX()) {
                output.setText("");
                return true;
            }
        } else {
            if (currentCell.getCoordX() == lastCell.getCoordX() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY() + Integer.parseInt(lastCell.getId())
                    || currentCell.getCoordX() == lastCell.getCoordX() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY() - Integer.parseInt(lastCell.getId())
                    || currentCell.getCoordY() == lastCell.getCoordY() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX() - Integer.parseInt(lastCell.getId())
                    || currentCell.getCoordY() == lastCell.getCoordY() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX() + Integer.parseInt(lastCell.getId())) {
                output.setText("");
                return true;
            }
        }
        output.setText("Invalid move.");
        return false;
    }

}
