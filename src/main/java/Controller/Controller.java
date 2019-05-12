package Controller;

import Model.Board;
import Model.Cell;
import Model.Leaderboard;
import Model.Player;
import View.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.time.Instant;
import java.util.Random;


public class Controller {
    private View view;
    private Board board;
    private Cell lastCell;
    private Cell currentCell;
    private Player player;
    private Leaderboard leaderboard;
    private Scene scene;
    private Label output;

    public void createScene(Parent sceneRoot){
        scene = new Scene(sceneRoot);
    }

    public Scene getScene(){
        return scene;
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

    public void createPlayer(){ player = new Player(); }

    public Player getPlayer() { return player; }

    public void createLeaderboard(){ leaderboard = new Leaderboard(); }

    public Leaderboard getLeaderboard(){ return leaderboard; }

    public void createOutput(){
        output = new Label();
    }

    public Label getOutput(){
        return output;
    }

    public void fillBoard(int rowsNumber, int columnsNumber) {
        Random random = new Random();
        for (int x = 0; x < rowsNumber; x++) {
            for (int y = 0; y < columnsNumber; y++) {
                Cell cell = new Cell(x, y, 30);
                cell.setId(Integer.toString(random.nextInt(4) + 1));
                cell.setMarkStyle("-fx-base: #ee2211;");
                cell.setUnmarkStyle("-fx-base: #ffffff;");
                if (random.nextFloat() <= 0.20) {
                    cell.setBlock(true);
                    cell.setText("[" + cell.getId() + "]");
                }
                else{
                    cell.setBlock(false);
                    cell.setText(cell.getId());
                }

                cell.unmark();

                //Starting point of the board
                if (x == 0 && y == 0) {
                    lastCell = cell;
                    lastCell.mark();
                }

                if(x == rowsNumber - 1 && y == columnsNumber - 1){
                    cell.setId("100");
                    cell.setText("*");
                    cell.setOnAction(addWinEvent());
                }
                else
                    cell.setOnAction(addMoveEvent());

                board.add(cell, x, y, 1, 1);
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
