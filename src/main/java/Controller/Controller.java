package Controller;

import Model.Board;
import Model.Cell;
import View.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;


public class Controller {
    private View view;
    private Board board;
    private Cell lastCell;
    private Cell currentCell;
    private Scene scene;

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

    public void showView(Stage stage) {
        view.launchView(stage);
    }

    public void createBoard(){
        board = new Board();
    }

    public Board getBoard(){
        return board;
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

                if(x == rowsNumber && y == columnsNumber)
                    c.setEvent(addWinEvent());
                else
                    c.setEvent(addMoveEvent());

                board.add(c, x, y);
            }
        }
    }

    public EventHandler<ActionEvent> addMoveEvent() {
        return event -> {
            currentCell = (Cell) event.getSource();
            if (canMoveToCell()) {
                lastCell.unmark();
                currentCell.mark();
                lastCell = currentCell;
                lastCell.mark();
            }
        };
    }

    public EventHandler<ActionEvent> addWinEvent() {
        return event -> {
            System.out.println("Congratulations!!! You Won.");
        };
    }

    public Boolean canMoveToCell() {
        if (!lastCell.isBlocked()) {
            if (currentCell.getCoordX() == lastCell.getCoordX() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY()
                    || currentCell.getCoordX() == lastCell.getCoordX() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY()
                    || currentCell.getCoordY() == lastCell.getCoordY() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX()
                    || currentCell.getCoordY() == lastCell.getCoordY() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX()) {
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
                return true;
            }
        }
        return false;
    }

}
