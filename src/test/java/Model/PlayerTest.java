package Model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {
        Player player = new Player("Player1");
        assertEquals("Player1", player.getName());
    }

    @Test
    void setName() {
        Player player = new Player("Player1");
        player.setName("Player2");
        assertEquals("Player2", player.getName());
    }

    @Test
    void getScore() {
        Player player = new Player("Player1", 1000.0);
        assertEquals(1000.0, player.getScore());
    }

    @Test
    void setScore() {
        Player player = new Player("Player1", 1000.0);
        player.setScore(100.0);
        assertEquals(100.0, player.getScore());
    }
}