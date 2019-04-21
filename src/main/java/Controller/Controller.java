package Controller;


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Controller {

    private static Controller instance = null;
    private Object[] boards;
    private Object[] stones;
    private Object[] players;
    private Object[] leaderboards;
    private Object[] views;

    private Controller(){

    }

    //Singleton Design Pattern
    public static Controller getInstance(){
        try {
            if (instance == null) {
                synchronized(Controller.class) {
                    instance = new Controller();
                }
            }
            return instance;
        } catch (Exception e) {}

        return instance;
    }

    public Boolean addBoard(Object board){
        return true;
    }

    public Object getBoard(int index) {
        return boards[index];
    }

    public Boolean deleteBoard(int index) {
        return true;
    }

    public Boolean addStone(Object stone){
        return true;
    }

    public Object getStone(int index) {
        return stones[index];
    }

    public Boolean deleteStone(int index) {
        return true;
    }

    public Boolean addPlayer(Object player){
        return true;
    }

    public Object getPlayer(int index) {
        return players[index];
    }

    public Boolean deletePlayer(int index) {
        return true;
    }

    public Boolean addLeaderboard(Object leaderboard){
        return true;
    }

    public Object getLeaderboard(int index) {
        return leaderboards[index];
    }

    public Boolean deleteLeaderboard(int index) {
        return true;
    }

    public Boolean addView(Object view){
        return true;
    }

    public Object getView(int index) {
        return views[index];
    }

    public Boolean deleteView(int index) {
        return true;
    }

    public Boolean updateView(Pane pane){
        return true;
    }

    public Boolean cleanView(Pane pane){
        return true;
    }

    public Label draw(Object object){
        return new Label();
    }

}
