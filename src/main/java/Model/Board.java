package Model;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;


public class Board extends Parent {
    private GridPane board;

    public Board(){
        board = new GridPane();
        getChildren().add(board);
    }

    public GridPane getBoard(){
        return board;
    }

    public void setBoard(GridPane board){
        this.board = board;
    }

    public void add(Object object, Integer coordX, Integer coordY){
        board.add((Node)object, coordX, coordY, 1, 1);
    }

}