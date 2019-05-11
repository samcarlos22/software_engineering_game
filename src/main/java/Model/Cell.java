package Model;

import javafx.scene.control.Button;


public class Cell extends Button {
    private Button cell;
    private Integer coordX, coordY;
    private String markStyle, unmarkStyle;
    private Boolean block;
    private Integer size;

    public Cell() {
        cell = new Button();
    }

    public Cell(Integer coordX, Integer coordY) {
        cell = new Button();
        setCoordX(coordX);
        setCoordY(coordY);
    }

    public Cell(Integer coordX, Integer coordY, Integer size) {
        cell = new Button();
        setCoordX(coordX);
        setCoordY(coordY);
        setSize(size);
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        setMinSize(size, size);
        setMaxSize(size, size);
        setPrefSize(size, size);
    }

    public Boolean isBlocked() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public String getMarkStyle() {
        return markStyle;
    }

    public void setMarkStyle(String markStyle) {
        this.markStyle = markStyle;
    }

    public String getUnmarkStyle() {
        return unmarkStyle;
    }

    public void setUnmarkStyle(String unmarkStyle) {
        this.unmarkStyle = unmarkStyle;
    }

    public void mark() {
        setStyle(markStyle);
    }

    public void unmark() {
        setStyle(unmarkStyle);
    }

    public Integer getCoordX() {
        return coordX;
    }

    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }

    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }
}