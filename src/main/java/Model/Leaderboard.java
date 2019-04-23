package Model;

import java.util.*;
import java.util.stream.*;
import org.javatuples.Pair;

public class Leaderboard {

    private Object[] leaderboard;

    public Object[] getLeaderboard() {
        return leaderboard;
    }

    public boolean setLeaderboard(Object[] leaderboard) {
        try {
            this.leaderboard = leaderboard;
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Leaderboard(int leaderboardSize) {
        this.leaderboard = new Object[leaderboardSize];
    }

    public Leaderboard() {
    }
}

