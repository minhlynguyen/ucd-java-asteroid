package asteroid_app.initial;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

// A class to keep track of the player's remaining lives.
public class PlayerLives {
    private IntegerProperty lives = new SimpleIntegerProperty(3);

    // Method that returns the current number of lives.
    public int getLives() {
        return lives.get();
    }

    // Method that decrements the number of lives by one.
    public void loseLife() {
        lives.set(lives.get() - 1);
    }

    // JavaFX property accessor for lives.
    public IntegerProperty livesProperty() {
        return lives;
    }
}
