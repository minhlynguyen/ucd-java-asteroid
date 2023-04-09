package asteroid_app.initial;

public class playerlives {
    private int lives;

    // Constructor that takes the number of lives as a parameter
    public playerlives(int lives) {
        this.lives = lives;
    }

    // Getter method for the number of lives
    public int getLives() {
        return lives;
    }

    // Method to decrease the number of lives by 1
    public void loseLife() {
        lives--;
    }

    // Method to increase the number of lives by 1
    public void gainLife() {
        lives++;
    }
}
