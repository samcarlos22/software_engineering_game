package Model;

public class Leaderboard {

    private int[] leaderboard;

    public int[] getLeaderboard() {
        return leaderboard;
    }

    public boolean setLeaderboard(int[] leaderboard) {
        try {
            this.leaderboard = leaderboard;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Leaderboard(int leaderboardSize) {
        this.leaderboard = new int[leaderboardSize];
    }
}
