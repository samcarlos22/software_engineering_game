
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Board extends Parent {
    private VBox rows = new VBox();
    private Random random = new Random();
    private Cell startCell;

    public Board(Integer rowsNumber, Integer columnsNumber, EventHandler<? super MouseEvent> handler) {

        for (int y = 0; y < rowsNumber; y++) {
            HBox row = new HBox();
            for (int x = 0; x < columnsNumber; x++) {
                float blockChance = random.nextFloat();
                Boolean isBlocked = false;
                if (blockChance <= 0.20) {
                    isBlocked = true;
                }

                Cell c = new Cell(x, y, isBlocked, random.nextInt(4) + 1);
                if (x == 0 && y == 0)
                    startCell = c;
                c.setOnMouseClicked(handler);
                row.getChildren().add(c);
            }
            rows.getChildren().add(row);
        }
        getChildren().add(rows);

    }

    public Cell getStartCell(){
        return startCell;
    }
}