package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.javatuples.Pair;
import static org.junit.jupiter.api.Assertions.*;

class LeaderboardTest {

    private Leaderboard leaderboard = new Leaderboard(10);

    @Test
    void getLeaderboard() {
        assertArrayEquals(new Object[10], this.leaderboard.getLeaderboard());
    }

    @Test
    void setLeaderboard() {
        assertTrue(this.leaderboard.setLeaderboard(new Object[]{Pair.with("Player1", 1000)}));
    }
}
