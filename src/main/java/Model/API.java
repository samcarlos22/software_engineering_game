package Model;

public class API {

    public Board createBoard(int rowSize, int columnSize){
        return new Board(rowSize, columnSize);
    }

    public Leaderboard createLeaderboard(int leaderboardSize){
        return new Leaderboard(leaderboardSize);
    }

    public Player createPlayer(String name){
        return new Player(name);
    }

    public Stone createStone(){
        return new Stone();
    }
}
