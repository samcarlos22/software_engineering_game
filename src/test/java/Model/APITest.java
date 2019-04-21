package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class APITest {

    private API api = new API();

    @Test
    void createBoard() {
        Board board = new Board(10,10);
        assertEquals(board.getClass(), this.api.createBoard(10,10).getClass());
    }

    @Test
    void createLeaderboard() {
        Leaderboard leaderboard = new Leaderboard(10);
        assertEquals(leaderboard.getClass(), this.api.createLeaderboard(10).getClass());
    }

    @Test
    void createPlayer() {
        Player player = new Player("Player 1");
        assertEquals(player.getClass(), this.api.createPlayer("Player1").getClass());
    }

    @Test
    void createStone() {
        Stone stone = new Stone();
        assertEquals(stone.getClass(), this.api.createStone().getClass());
    }
}