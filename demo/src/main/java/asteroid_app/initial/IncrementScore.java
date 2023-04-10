package asteroid_app.initial;

// A class to keep track of the player's score.
public class IncrementScore {
    public static final int POINTS_FOR_ASTEROID = 10;
    public static final int POINTS_FOR_ALIEN = 50;

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

    // Method that increments the score by the standard amount for an asteroid.
    public void incrementScoreForAsteroid() {
        incrementScore(POINTS_FOR_ASTEROID);
    }

    // Method that increments the score by the standard amount for an alien.
    public void incrementScoreForAlien() {
        incrementScore(POINTS_FOR_ALIEN);
    }
}
