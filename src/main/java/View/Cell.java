package View;

import javafx.scene.control.Button;


public class Cell extends Button {
    private final Integer x, y;
    private final Boolean block;

    public Cell(int x, int y, Boolean block) {
        this.x = x;
        this.y = y;
        this.block = block;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Boolean isBlocked() {
        return block;
    }
}
