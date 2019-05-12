package Model;

import javafx.scene.layout.GridPane;


public class Board extends GridPane{
    private GridPane board;

    public Board(){
        board = new GridPane();
    }

    public GridPane getBoard(){
        return board;
    }

}