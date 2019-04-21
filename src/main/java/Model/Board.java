package Model;

public class Board {

    private int[][] board;

    public int[][] getBoard() {
        return board;
    }

    public boolean setBoard(int[][] board) {
        try {
            this.board = board;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Board(int rowSize, int columnSize) {
        this.board = new int[rowSize][columnSize];
    }
}
