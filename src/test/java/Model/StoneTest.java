package Model;

import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoneTest {

    private Stone stone = new Stone(new Pair<Integer, Integer>(5,5));

    @Test
    void setPosition() {
        assertTrue(this.stone.setPosition(new Pair<>(5,5)));
    }

    @Test
    void getPosition() {
        assertEquals(new Pair<>(5,5), this.stone.getPosition());
    }

}
