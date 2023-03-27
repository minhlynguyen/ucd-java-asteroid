package asteroid_app.initial;

public class PlayerLives {
    private int lives;

    public PlayerLives() {
        this.lives = 3; // Default value is 3
    }

    public boolean loseLife() {
        lives--;
        return lives > 0;
    }

    public void gainLife() {
        lives++;
    }

    public int getLives() {
        return lives;
    }
}
