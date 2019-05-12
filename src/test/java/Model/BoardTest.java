package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void Board() {
        Board board = new Board();
        assertEquals(new Board().getClass(), Board.class);
    }

    @Test
    void getBoard() {
        assertEquals(new Board().getClass(), Board.class);
    }
}