package Model;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;


public class Board extends GridPane{
    private GridPane board;

    public Board(){
        board = new GridPane();
    }

    public GridPane getBoard(){
        return board;
    }

    public void setBoard(GridPane board){
        this.board = board;
    }

}