package Controller;

import Model.Board;
import Model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void canMove() {
        Controller controller = new Controller();
        /*Move 1 cell right
            {S, T, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        */
        assertEquals(controller.canMove("1,0,0,false","1,0,1,false"), true);

        /*Move 4 cells left
            {T, 0, 0, 0, S, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        */
        assertEquals(controller.canMove("4,0,4,false","1,0,0,false"), true);

        /*Move 2 cells down
            {S, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {T, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        */
        assertEquals(controller.canMove("2,0,0,false","1,2,0,false"), true);

        /*Move 2 cells up
            {T, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {S, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        */
        assertEquals(controller.canMove("2,2,0,false","1,0,0,false"), true);

        /*Move 4 cells right down diagonal
            {S, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, T, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        */
        assertEquals(controller.canMove("4,0,0,true","1,4,4,false"), true);

        /*Move 3 cells right top diagonal
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, T, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {S, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        */
        assertEquals(controller.canMove("3,4,0,true","1,1,3,false"), true);

        /*Wrong right movement (Can move: 2, Try to move: 4)
            {S, 0, T, 0, X, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0}
        */
        assertNotEquals(controller.canMove("2,0,0,false","1,0,4,false"), true);


    }

    @Test
    void createBoard() {
        Controller controller = new Controller();
        controller.createBoard(8,8);
        assertEquals(controller.getBoard().getClass(), String[][].class);
    }

    @Test
    void createPlayer() {
        Controller controller = new Controller();
        controller.createPlayer("Player1");
        assertEquals(controller.getPlayer().getClass(), Player.class);
    }

    @Test
    void getPlayer() {
        Controller controller = new Controller();
        controller.createPlayer("Player1");
        assertNotEquals(controller.getPlayer(), null);
    }

    @Test
    void getBoard() {
        Controller controller = new Controller();
        controller.createBoard(8,8);
        assertNotEquals(controller.getBoard().getClass(), null);
    }

    @Test
    void isGoal() {
        Controller controller = new Controller();
        assertEquals(controller.isGoal("*"), true);
        assertEquals(controller.isGoal("False"), false);
    }
}