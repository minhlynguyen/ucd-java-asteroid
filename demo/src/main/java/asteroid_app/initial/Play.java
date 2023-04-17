package asteroid_app.initial;

public class Player {
    private PlayerLives playerLives;
    private IncrementScore incrementScore;

    public Player() {
        playerLives = new PlayerLives();
        incrementScore = new IncrementScore();
    }

    public PlayerLives getPlayerLives() {
        return playerLives;
    }

    public IncrementScore getIncrementScore() {
        return incrementScore;
    }
}
