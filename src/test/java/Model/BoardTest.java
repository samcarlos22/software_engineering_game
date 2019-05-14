package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void fillBoard() {
        Board board = new Board(3,3);
        board.fillBoard();
        assertNotEquals(board.getBoard()[0][0], null);
    }

    @Test
    void setBoard() {
        Board board = new Board(10,10);
        board.setBoard(new String[20][20]);
        assertEquals(20, board.getBoard().length);
        assertEquals(20, board.getBoard()[0].length);
    }

    @Test
    void getBoard() {
        Board board = new Board(10,10);
        String[][] matrix = board.getBoard();
        assertEquals(10, matrix.length);
        assertEquals(10, matrix[0].length);
    }
}