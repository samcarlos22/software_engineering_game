package Model;


import java.util.Random;


public class Board{
    private String[][] board;

    public Board(Integer columnSize, Integer rowSize){
        board = new String[rowSize][columnSize];
    }

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

    public void setBoard(String[][] board){
        this.board = board;
    }

    public String[][] getBoard(){
        return board;
    }

}