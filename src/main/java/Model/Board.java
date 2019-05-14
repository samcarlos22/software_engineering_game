package Model;


import java.util.Random;

/**
 * Class representing the game's board.
 */
public class Board{

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
            for (int y = 0; y < board[0].length; y++) {
                Double blockChance = random.nextDouble();

                board[x][y] = (random.nextInt(4) + 1)
                               + "," + Integer.toString(x)
                               + "," + Integer.toString(y)
                               + "," + Double.toString(random.nextDouble());
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

}