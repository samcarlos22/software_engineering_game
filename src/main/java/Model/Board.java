package Model;

import org.tinylog.Logger;

/**
 * Class representing the game's board.
 */
public class Board{

    /**
     * The matrix representing the game's board.
     */
    private final int[][] board = new int[][] {

            {4, 2, 2, 4, 4, 3, 4, 3},
            {3, 5, 3, 4, 2, 3, 5, 2},
            {4, 3, 2, 5, 2, 2, 5, 2},
            {7, 1, 4, 4, 4, 2, 2, 3},
            {3, 2, 2, 4, 2, 5, 2, 5},
            {2, 3, 2, 4, 4, 2, 5, 1},
            {6, 2, 2, 3, 2, 5, 6, 3},
            {1, 2, 5, 4, 4, 2, 1, 0}

    };

    /**
     * The matrix representing the board block status of every cell.
     */
    private final Boolean[][] blockMap = new Boolean[][] {

            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false}

    };

    /**
     * The initial board's state
     */
    private final int[][] INITIAL_STATE = new int[][]{

            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}

    };

    /**
     * The final board's state
     */
    private final int[][] GOAL_STATE = new int[][]{

            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1}

    };

    /**
     * The current board's state
     */
    private int[][] CURRENT_STATE;

    /**
     * Creates a {@code Board} object and sets
     * its first movement state.
     */
    public Board(){

        move(0, 0);

    }

    /**
     * Returns the game's board.
     * @return the game's board.
     */
    public int[][] getBoard(){
        return board;
    }

    /**
     * Returns the game's block map.
     * @return the game's block map.
     */
    public Boolean[][] getBlockMap(){
        return blockMap;
    }

    /**
     * Returns the game's current state.
     * @return the game's current state.
     */
    public int[][] getCurrentState(){
        return CURRENT_STATE;
    }

    /**
     * Returns the game's initial state.
     * @return the game's initial state.
     */
    public int[][] getInitialState(){
        return INITIAL_STATE;
    }

    /**
     * Returns the game's goal state.
     * @return the game's goal state.
     */
    public int[][] getFinalState(){
        return GOAL_STATE;
    }

    /**
     * Compares if two given states are identical
     * @return True if the given states are identical.
     *         False if the given states are not identical.
     */
    public Boolean compareStates(int[][] state1, int[][] state2){
        try {
            int i, j;
            if (state1.length == state2.length && state1[0].length == state2[0].length) {
                for (i = 0; i < state1.length; i++)
                    for (j = 0; j < state2.length; j++)
                        if (state1[i][j] != state2[i][j])
                            return false;
            }
            return true;
        }catch (Exception e) {
            Logger.error("Invalid matrices.");
            return false;
        }
    }

    /**
     * Sets the game's current movement state.
     *
     */
    public void move(int coordX, int coordY){
        CURRENT_STATE = INITIAL_STATE;
        CURRENT_STATE[coordX][coordY] = 1;
    }

}