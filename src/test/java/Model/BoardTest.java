package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board = new Board(10,10);

    @Test
    void getBoard() {
        assertArrayEquals(new int[10][10], this.board.getBoard());
    }

    @Test
    void setBoard() {
        assertTrue(this.board.setBoard(new int[15][15]));
    }
}