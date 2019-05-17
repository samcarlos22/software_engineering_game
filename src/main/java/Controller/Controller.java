package Controller;

import Model.Player;
import Model.Board;
import View.View;
import org.tinylog.Logger;

import java.util.HashMap;

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
     * A tuple containing the coordinates of the last valid cell
     * selected by the user.
     */
    private HashMap<String, Integer> lastCell = new HashMap<>();

    /**
     * A tuple containing the coordinates of the current cell
     * selected by the user.
     */
    private HashMap<String, Integer> currentCell = new HashMap<>();

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
     * @see Board
     */
    public void createBoard(){
        try{
            board = new Board();
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
    public int[][] getBoard() {
        return board.getBoard();
    }

    /**
     * Returns the actual game's blocked cells map.
     * @return the actual game's blocked cells map.
     */
    public Boolean[][] getBlockMap() {
        return board.getBlockMap();
    }

    /**
     * Moves a cell throughout the board and changes the {@code Board}
     * current state.
     * @param numberOfWalks the number of tiles the user wants to move.
     * @param coordX the X coordinate to move to.
     * @param coordY the Y coordinate to move to.
     * @return True if the player's movement is valid;
     *         False if the player's movement is not valid.
     * @see Board
     */
    public Boolean move(int numberOfWalks, int coordX, int coordY){
        setCurrentCell(coordX, coordY);
        if (canMove(numberOfWalks)){
            setLastCell(coordX, coordY);
            board.move(coordX, coordY);
            board.getCurrentState();
            return true;
        }
        return false;
    }
    
    /**
     * Returns {@code True} or {@code False} whether the player's movement
     * is valid or not.
     * @param numberOfWalks the number of tiles the user wants to move.
     * @see View
     * @return True if the player's movement is valid;
     *         False if the player's movement is not valid.
     */
    public Boolean canMove(int numberOfWalks) {
                
        if (!board.getBlockMap()[getLastCell().get("X")][getLastCell().get("Y")]) {
            if (getCurrentCell().get("X") == getLastCell().get("X") + numberOfWalks
                    && getCurrentCell().get("Y") == getLastCell().get("Y")
                    || getCurrentCell().get("X") == getLastCell().get("X") - numberOfWalks
                    && getCurrentCell().get("Y") == getLastCell().get("Y")
                    || getCurrentCell().get("Y") == getLastCell().get("Y") + numberOfWalks
                    && getCurrentCell().get("X") == getLastCell().get("X")
                    || getCurrentCell().get("Y") == getLastCell().get("Y") - numberOfWalks
                    && getCurrentCell().get("X") == getLastCell().get("X")) {
                Logger.info("Move to (" + getCurrentCell().get("X") + ", " + getCurrentCell().get("Y")+ ")");
                return true;
            }
        } else {
            if (getCurrentCell().get("X") == getLastCell().get("X") + numberOfWalks
                    && getCurrentCell().get("Y") == getLastCell().get("Y") + numberOfWalks
                    || getCurrentCell().get("X") == getLastCell().get("X") - numberOfWalks
                    && getCurrentCell().get("Y") == getLastCell().get("Y") - numberOfWalks
                    || getCurrentCell().get("Y") == getLastCell().get("Y") + numberOfWalks
                    && getCurrentCell().get("X") == getLastCell().get("X") - numberOfWalks
                    || getCurrentCell().get("Y") == getLastCell().get("Y") - numberOfWalks
                    && getCurrentCell().get("X") == getLastCell().get("X") + numberOfWalks) {
                Logger.info("Move to (" + getCurrentCell().get("X") + ", " + getCurrentCell().get("Y")+ ")");
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code True} or {@code False} whether the player's wins
     * the game.
     * @return True if the player's wins the game by moving to this cell;
     *         False if the player's didn't win the game by moving to this cell.
     */
    public Boolean isGoal() {
        Boolean isGoal = board.compareStates(board.getCurrentState(), board.getFinalState());
        if (isGoal) {
            Logger.info("Game Finished");
            return true;
        }
        return false;
    }

    /**
     * Sets the last valid cell selected by the user.
     * @param coordX the coordinate X of the last valid cell selected by the user.
     * @param coordY the coordinate Y of the last valid cell selected by the user.
     */
    public void setLastCell(int coordX, int coordY){
        lastCell.replace("X", coordX);
        lastCell.replace("Y", coordY);
    }

    /**
     * Returns the last valid cell selected by the user.
     * @return the last valid cell selected by the user.
     */
    public HashMap<String, Integer> getLastCell(){
        return lastCell;
    }

    /**
     * Sets the current cell selected by the user.
     * @param coordX the coordinate X of the current cell selected by the user.
     * @param coordY the coordinate Y of the current cell selected by the user.
     */
    public void setCurrentCell(int coordX, int coordY){
        currentCell.replace("X", coordX);
        currentCell.replace("Y", coordY);
    }

    /**
     * Returns the current cell selected by the user.
     * @return the current cell selected by the user.
     */
    public HashMap<String, Integer> getCurrentCell(){
        return currentCell;
    }

    /**
     * Returns the current {@code Board}'s state.
     * @return the current {@code Board}'s state.
     */
    public int[][] getCurrentState(){
        return board.getCurrentState();
    }

    public Controller(){
        lastCell.put("X", 0);
        lastCell.put("Y", 0);
        currentCell.put("X", 0);
        currentCell.put("Y", 0);
    }
    
}
