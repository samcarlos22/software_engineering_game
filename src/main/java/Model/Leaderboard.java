package Model;

import javafx.scene.layout.VBox;

public class Leaderboard extends VBox {
    private VBox leaderboard;

    public Leaderboard(){
        leaderboard = new VBox();
    }

    public VBox getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(VBox leaderboard) {
        this.leaderboard = leaderboard;
    }

}
