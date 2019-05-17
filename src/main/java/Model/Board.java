package Model;


import java.util.Random;

/**
 * Class representing the game's board.
 */
public class Board{

    /**
     * The initial board's state
     */
    private final int[][] INITIAL_STATE = new int[][]{

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

            {false, false, true, false, false, true, false, true},
            {false, false, false, false, false, false, false, true},
            {false, false, false, false, false, false, false, false},
            {false, false, false, true, false, false, false, false},
            {true, false, false, false, false, false, false, false},
            {false, true, false, false, false, false, false, true},
            {false, false, false, true, false, false, false, false},
            {false, true, false, false, false, false, true, false}

    };

    /**
     * The matrix representing the game's board.
     */
    private String[][] board;

    /**
     * Creates a {@code Board} object.
     * @param columnSize the board's number of columns.
     * @param rowSize the board's number of rows.
     */
    public Board(Integer columnSize, Integer rowSize){
        board = new String[rowSize][columnSize];
    }

    /**
     * Fills the board with random generated numbers from 1 to 4.
     */
    public void fillBoard() {
        Random random = new Random();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {

                board[x][y] = (INITIAL_STATE[x][y])
                               + "," + Integer.toString(x)
                               + "," + Integer.toString(y)
                               + "," + Boolean.toString(blockMap[x][y]);
            }
        }
    }

    /**
     * Sets the game's board.
     * @param board the game's board.
     */
    public void setBoard(String[][] board){
        this.board = board;
    }

    /**
     * Returns the game's board.
     * @return the game's board.
     */
    public String[][] getBoard(){
        return board;
    }

    public int[][] getInitialState() {
        return INITIAL_STATE;
    }
}