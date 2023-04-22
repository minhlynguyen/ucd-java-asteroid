package asteroid_app.initial;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

// A class that represents the lives of a player in a game.
public class PlayerLives {
    private IntegerProperty lives;
    private long ctime=0L;
    // Constructor that initializes the PlayerLives object with 3 lives.
    public PlayerLives() {
        this.lives = new SimpleIntegerProperty(3); // Default value is 3
    }

    // Method that decrements the number of lives by 1.
// Returns true if the player still has lives left, false otherwise.
    public boolean loseLife() {
        if(ctime!=0L){
            long time=   System.currentTimeMillis()-ctime;
            if(time<=3000)
                return false;
        }
        ctime=System.currentTimeMillis();
        int currentLives = lives.get();
        currentLives--;
        lives.set(currentLives);
        return currentLives > 0;
    }

    // Method that increments the number of lives by 1.
    public void gainLife() {
        int currentLives = lives.get();
        currentLives++;
        lives.set(currentLives);
    }

    // Method that returns the current number of lives.
    public IntegerProperty livesProperty() {
        return lives;
    }

    public int getLives() {
        return lives.get();
    }
}