package Model;

import javafx.scene.control.Label;

public class Player extends Label {
    private Label name;
    private Label score;

    public Player(){
        name = new Label();
        score = new Label();
    }

    public String getName() {
        return name.getText();
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public String getScore() { return score.getText(); }

    public void setScore(String score) {
        this.score.setText(score);
    }
}
