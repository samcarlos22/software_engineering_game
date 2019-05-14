package Controller;

import Model.Board;
import Model.Player;
import View.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    /*@Test
    void createView() {
        Controller controller = new Controller();
        controller.createView();
        assertEquals(controller.getView().getClass(), View.class);
    }*/

    /*@Test
    void getView() {
        Controller controller = new Controller();
        controller.createView();
        assertNotEquals(controller.getView(), null);
    }*/

    @Test
    void createBoard() {
        Controller controller = new Controller();
        controller.createBoard(10,10);
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
        controller.createBoard(10,10);
        assertNotEquals(controller.getBoard().getClass(), null);
    }

    @Test
    void canMove() {
        Controller controller = new Controller();
        assertEquals(controller.canMove("1,0,0,0","1,1,1,1"), true);
        assertEquals(controller.canMove("1,0,0,0","1,0,0,1"), false);
    }

    @Test
    void isGoal() {
    }
}