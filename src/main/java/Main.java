import java.util.Random;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    private Board playerBoard;
    private Cell lastCell;
    private Cell currentCell;
    private Integer boardRowsSize = 10;
    private Integer boardColumnsSize = 10;

    private Random random = new Random();

    private Parent createContent() {
        BorderPane root = new BorderPane();
        root.setPrefSize(400, 400);

        //root.setRight(new Text("Leaderboard"));
        //root.setTop(new Text("Welcome to the Game"));
        playerBoard = new Board(boardRowsSize, boardColumnsSize, event -> {
            currentCell = (Cell) event.getSource();
            //System.out.println(currentCell.getText());
            if (canMove()) {
                lastCell.unmark();
                currentCell.mark();
                lastCell = currentCell;
                if (lastCell.getText().equals("*"))
                    System.out.println("You Won!!");
                lastCell.mark();
            }
        });

        lastCell = playerBoard.getStartCell();
        lastCell.mark();

        root.setCenter(playerBoard);

        return root;
    }

    public Boolean canMove() {
        if (!lastCell.isBlocked()) {
            if (currentCell.getCoordX() == lastCell.getCoordX() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY()
                    || currentCell.getCoordX() == lastCell.getCoordX() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY()
                    || currentCell.getCoordY() == lastCell.getCoordY() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX()
                    || currentCell.getCoordY() == lastCell.getCoordY() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX()) {
                return true;
            }
        } else {
            if (currentCell.getCoordX() == lastCell.getCoordX() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY() + Integer.parseInt(lastCell.getId())
                    || currentCell.getCoordX() == lastCell.getCoordX() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordY() == lastCell.getCoordY() - Integer.parseInt(lastCell.getId())
                    || currentCell.getCoordY() == lastCell.getCoordY() + Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX() - Integer.parseInt(lastCell.getId())
                    || currentCell.getCoordY() == lastCell.getCoordY() - Integer.parseInt(lastCell.getId())
                    && currentCell.getCoordX() == lastCell.getCoordX() + Integer.parseInt(lastCell.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
