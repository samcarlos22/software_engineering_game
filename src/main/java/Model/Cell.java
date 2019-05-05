package Model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class Cell extends Button {
    private Integer x, y;

    public Boolean isBlocked;


    public Cell(Integer x, int y, Boolean isBlocked, Integer id) {
        super();
        setMinSize(30, 30);
        setMaxSize(30, 30);
        setPrefSize(30,30);
        if(x == 9 && y == 9) setText("*");
        else if(isBlocked) setText("[" + id + "]");
        else setText(Integer.toString(id));
        this.x = x;
        this.y = y;
        this.isBlocked = isBlocked;
        setId(Integer.toString(id));
        setStyle("-fx-base: #ffffff;");
    }

    public Boolean isBlocked(){
        return isBlocked;
    }

    public Boolean mark() {
        setStyle("-fx-base: #ee2211;");
        return true;
    }

    public Boolean unmark() {
        setStyle("-fx-base: #ffffff;");
        return true;
    }

    public void setEvent(EventHandler<ActionEvent> event){
        setOnAction(event);
    }

    public Integer getCoordX(){
        return x;
    }

    public Integer getCoordY(){
        return y;
    }
}