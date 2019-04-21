package Model;

import org.javatuples.Pair;

public class Stone {

    private Pair<Integer, Integer> position;

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public boolean setPosition(Pair<Integer, Integer> position) {
        try {
            this.position = position;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Stone() {
        this.position = new Pair<Integer,Integer>(0,0);
    }
}
