package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    private Leaderboard leaderboard = new Leaderboard(10);

    @Test
    void getLeaderboard() {
        assertArrayEquals(new int[10], this.leaderboard.getLeaderboard());
    }

    @Test
    void setLeaderboard() {
        assertTrue(this.leaderboard.setLeaderboard(new int[15]));
    }
}