package Controller;

import Model.Leaderboard;
import Model.Player;
import Model.Board;
import View.View;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    private Board board;
    private View view;

    public void createView(){
        view = new View(this);
        Logger.info("New View Created");
    }

    public View getView(){
        return view;
    }

    public void createBoard(Integer columnSize, Integer rowSize){
        try{
            board = new Board(columnSize, rowSize);
            board.fillBoard();
            Logger.info("New Board Created");
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

    public Boolean canMove(String lastCell, String currentCell) {
        
        List<Integer> lastCellAttributes = new ArrayList<>();
        List<Integer> currentCellAttributes = new ArrayList<>();
        double lastCellBlockPercent = 0.0;
        String[] attributes;

        attributes = currentCell.split(",");
        for (int i = 0; i < 3; i++) {
            currentCellAttributes.add(Integer.parseInt(attributes[i]));
        }

        attributes = lastCell.split(",");
        for (int i = 0; i < 3; i++) {
            lastCellAttributes.add(Integer.parseInt(attributes[i]));
        }

        lastCellBlockPercent = Double.parseDouble(attributes[3]);
        
        if (!(lastCellBlockPercent <= 0.20)) {
            if (currentCellAttributes.get(1) == lastCellAttributes.get(1) + lastCellAttributes.get(0)
                    && currentCellAttributes.get(2) == lastCellAttributes.get(2)
                    || currentCellAttributes.get(1) == lastCellAttributes.get(1) - lastCellAttributes.get(0)
                    && currentCellAttributes.get(2) == lastCellAttributes.get(2)
                    || currentCellAttributes.get(2) == lastCellAttributes.get(2) + lastCellAttributes.get(0)
                    && currentCellAttributes.get(1) == lastCellAttributes.get(1)
                    || currentCellAttributes.get(2) == lastCellAttributes.get(2) - lastCellAttributes.get(0)
                    && currentCellAttributes.get(1) == lastCellAttributes.get(1)) {
                Logger.info("Move to (" + currentCellAttributes.get(1) + ", " + currentCellAttributes.get(2)+ ")");
                return true;
            }
        } else {
            if (currentCellAttributes.get(1) == lastCellAttributes.get(1) + lastCellAttributes.get(0)
                    && currentCellAttributes.get(2) == lastCellAttributes.get(2) + lastCellAttributes.get(0)
                    || currentCellAttributes.get(1) == lastCellAttributes.get(1) - lastCellAttributes.get(0)
                    && currentCellAttributes.get(2) == lastCellAttributes.get(2) - lastCellAttributes.get(0)
                    || currentCellAttributes.get(2) == lastCellAttributes.get(2) + lastCellAttributes.get(0)
                    && currentCellAttributes.get(1) == lastCellAttributes.get(1) - lastCellAttributes.get(0)
                    || currentCellAttributes.get(2) == lastCellAttributes.get(2) - lastCellAttributes.get(0)
                    && currentCellAttributes.get(1) == lastCellAttributes.get(1) + lastCellAttributes.get(0)) {
                Logger.info("Move to (" + currentCellAttributes.get(1) + ", " + currentCellAttributes.get(2)+ ")");
                return true;
            }
        }
        return false;
    }

    public Boolean isGoal(String lastCellValue) {
        if (lastCellValue.equals("*")) {
            Logger.info("Game Finished");
            return true;
        }
        return false;
    }
}
