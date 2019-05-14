package Controller;

import Model.Player;
import Model.Board;
import View.View;
import org.tinylog.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the game's controller.
 */
public class Controller {

    /**
     * The actual game's board.
     */
    private Board board;

    /**
     * The actual game's GUI.
     */
    private View view;

    /**
     * The actual game's player.
     */
    private Player player;

    /**
     * Creates a new instance of {@code View}.
     * @see View
     */
    public void createView(){
        try {
            view = new View(this);
            Logger.info("New View Created");
        }catch (ExceptionInInitializerError e){
            Logger.error(e.getException());
        }
    }

    /**
     * Creates a new instance of {@code Board}.
     * @param columnSize the board's number of columns.
     * @param rowSize the board's number of rows.
     * @see Board
     */
    public void createBoard(Integer columnSize, Integer rowSize){
        try{
            board = new Board(columnSize, rowSize);
            board.fillBoard();
            Logger.info("New Board Created");
        }catch (ExceptionInInitializerError e){
            Logger.error(e.getException());
        }
    }

    /**
     * Creates a new instance of {@code Player}.
     * @param name the player's name.
     * @see Player
     */
    public void createPlayer(String name){
        try {
            player = new Player(name);
            Logger.info("New Player Created");
        }catch (ExceptionInInitializerError e){
            Logger.error(e.getException());
        }
    }

    /**
     * Returns the actual game's player.
     * @return the actual game's player.
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Returns the actual game's GUI.
     * @return the actual game's GUI.
     */
    public View getView(){
        return view;
    }

    /**
     * Returns the actual game's board data.
     * @return the actual game's board data.
     */
    public String[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Returns {@code True} or {@code False} whether the player's movement
     * is valid or not.
     * @param lastCell the last cell which player has moved to.
     * @param currentCell the current cell which player has clicked in.
     * @see View
     * @return True if the player's movement is valid;
     *         False if the player's movement is not valid.
     */
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

    /**
     * Returns {@code True} or {@code False} whether the player's wins
     * the game.
     * @param lastCellValue the value of the last cell which player has moved to.
     * @see View
     * @return True if the player's wins the game by moving to this cell;
     *         False if the player's didn't win the game by moving to this cell.
     */
    public Boolean isGoal(String lastCellValue) {
        if (lastCellValue.equals("*")) {
            Logger.info("Game Finished");
            return true;
        }
        return false;
    }
}
