package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player = new Player("Player1");

    @Test
    void getName() {
        assertEquals("Player1", this.player.getName());
    }

    @Test
    void setName() {
        assertTrue(this.player.setName("Player2"));
    }

    @Test
    void setActualScore() {
        assertTrue(this.player.setActualScore(1000));
    }

    @Test
    void getActualScore() {
        this.player.setActualScore(1000);
        assertEquals(1000, this.player.getActualScore());
    }

    @Test
    void setBestScore() {
        assertTrue(this.player.setBestScore(1000));
    }

    @Test
    void getBestScore() {
        this.player.setBestScore(1000);
        assertEquals(1000, this.player.getBestScore());
    }

}