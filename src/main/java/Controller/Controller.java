package Controller;

import Model.Leaderboard;
import Model.Player;
import Model.Board;
import org.tinylog.Logger;


public class Controller {

    private Board board;

    public void createBoard(Integer columnSize, Integer rowSize){
        try{
            board = new Board(columnSize, rowSize);
            board.fillBoard();
            Logger.info("Board Created.");
        }catch (ExceptionInInitializerError e){
            Logger.error(e.getException());
        }
    }

    public String[][] getBoard() {
        return board.getBoard();
    }

    public void setBoard(String[][] board) {
        this.board.setBoard(board);
    }

    /*
    private EventHandler<ActionEvent> addMoveEvent() {
        return event -> {
            currentCell = (Cell) event.getSource();
            if (canMove()) {
                lastCell.unmark();
                lastCell = currentCell;
                lastCell.mark();
            }
        };
    }

    private EventHandler<ActionEvent> addWinEvent(){
        return event -> {
            currentCell = (Cell) event.getSource();
            if (canMove()) {
                lastCell.unmark();
                lastCell = currentCell;
                lastCell.mark();
                output.setText("Congratulations!! You won.");
            }
        };
    }

    private Boolean canMove() {
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
    */
}
