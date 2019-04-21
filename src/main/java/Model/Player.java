package Model;

public class Player {

    private String name;
    private int actualScore;
    private int bestScore;

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        try {
            this.name = name;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int getActualScore() {
        return actualScore;
    }

    public boolean setActualScore(int actualScore) {
        try {
            this.actualScore = actualScore;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public int getBestScore() {
        return bestScore;
    }

    public boolean setBestScore(int bestScore) {
        try {
            this.bestScore = bestScore;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Player(String name) {
        this.name = name;
    }
}
