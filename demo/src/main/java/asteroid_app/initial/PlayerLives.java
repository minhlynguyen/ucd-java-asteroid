// package asteroid_app.initial;
package.demo.src.main.java.asteroid_app.initial

// A class that represents the lives of a player in a game.
public class PlayerLives {
    private int lives;

    // Constructor that initializes the PlayerLives object with 3 lives.
    public PlayerLives() {
        this.lives = 3; // Default value is 3
    }

    // Method that decrements the number of lives by 1.
    // Returns true if the player still has lives left, false otherwise.
    public boolean loseLife() {
        lives--;
        return lives > 0;
    }

    // Method that increments the number of lives by 1.
    public void gainLife() {
        lives++;
    }

    // Method that returns the current number of lives.
    public int getLives() {
        return lives;
    }
}
