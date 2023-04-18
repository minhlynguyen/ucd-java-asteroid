package asteroid_app.initial;

// A class to keep track of the player's score.
public class IncrementScore {
    public static final int POINTS_FOR_ASTEROID_SIZE3 = 20;
    public static final int POINTS_FOR_ASTEROID_SIZE2 = 50;
    public static final int POINTS_FOR_ASTEROID_SIZE1 = 100;
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
    public void incrementScoreForAsteroid(int size) {
        switch (size) {
            case 3:
                incrementScore(POINTS_FOR_ASTEROID_SIZE3);
                break;
            case 2:
                incrementScore(POINTS_FOR_ASTEROID_SIZE2);
                break;
            case 1:
                incrementScore(POINTS_FOR_ASTEROID_SIZE1);
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
