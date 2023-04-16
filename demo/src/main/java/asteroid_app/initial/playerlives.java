package asteroid_app.modified;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

// A class to keep track of the player's score.
public class IncrementScore {
    public static final int POINTS_FOR_ASTEROID = 10;
    public static final int POINTS_FOR_ALIEN = 50;
    public static final int POINTS_FOR_EXTRA_LIFE = 10000;

    private IntegerProperty score = new SimpleIntegerProperty(0);
    private int lives = 3;

    // Constructor that initializes the score to zero.
    public IncrementScore() {
        score.set(0);
    }

    // Method that returns the current score.
    public int getScore() {
        return score.get();
    }

    // Method that returns the current number of lives.
    public int getLives() {
        return lives;
    }

    // Method that increments the score by the specified amount.
    public void incrementScore(int points) {
        score.set(score.get() + points);
        if (score.get() >= POINTS_FOR_EXTRA_LIFE) {
            int extraLives = score.get() / POINTS_FOR_EXTRA_LIFE;
            lives += extraLives;
            score.set(score.get() % POINTS_FOR_EXTRA_LIFE);
        }
    }

    // Method that increments the score by the standard amount for an asteroid.
    public void incrementScoreForAsteroid() {
        incrementScore(POINTS_FOR_ASTEROID);
    }

    // Method that increments the score by the standard amount for an alien.
    public void incrementScoreForAlien() {
        incrementScore(POINTS_FOR_ALIEN);
    }

    // JavaFX property accessor for score.
    public IntegerProperty scoreProperty() {
        return score;
    }
}
