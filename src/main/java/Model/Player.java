package Model;


import javax.persistence.*;

/**
 * Class representing the a specific player of the game and its score.
 */
@Entity
@Table(name = "Player")
public class Player{
    /**
     * The player's id.
     */
    @Id
    private Long id;

    /**
     * The player's name.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The player's score.
     */
    @Column(nullable = false)
    private Double score;

    /**
     * Creates a {@code Player} object.
     * @param name the player's name.
     */
    public Player(String name){
        this.name = name;
    }

    /**
     * Creates a {@code Player} object.
     * @param name the player's name.
     * @param score the player's score.
     */
    public Player(String name, Double score){
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the player's name.
     * @return the player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     * @param name the player's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the player's score.
     * @return the player's score
     */
    public Double getScore() {
        return score;
    }

    /**
     * Sets the player's score.
     * @param score the player's score.
     */
    public void setScore(Double score) {
        this.score = score;
    }
}
