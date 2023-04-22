package asteroid_app.initial;

import static asteroid_app.initial.Size.*;

// A class to keep track of the player's score.
public class IncrementScore {
    public static final int POINTS_FOR_ASTEROID_LARGE = 20;
    public static final int POINTS_FOR_ASTEROID_MIDDLE = 50;
    public static final int POINTS_FOR_ASTEROID_SMALL = 100;
    public static final int POINTS_FOR_ALIEN = 300;

    private int score;

    // Constructor that initializes the score to zero.
    public IncrementScore() {
        score = 0;
    }

    // Method that returns the current score.
    public int getScore() {
        return score;
    }

    // Method that increments the score by the specified amount.
    public void incrementScore(int points) {
        score += points;
    }

    // Method that increments the score by the standard amount for an asteroid based on its size.
    public void incrementScoreForAsteroid(Size size) {
        switch (size) {
            case LARGE:
                incrementScore(POINTS_FOR_ASTEROID_LARGE);
                break;
            case MIDDLE:
                incrementScore(POINTS_FOR_ASTEROID_MIDDLE);
                break;
            case SMALL:
                incrementScore(POINTS_FOR_ASTEROID_SMALL);
                break;
            default:
                break;
        }
    }

    // Method that increments the score by the standard amount for an alien.
    public void incrementScoreForAlien() {
        incrementScore(POINTS_FOR_ALIEN);
    }
}