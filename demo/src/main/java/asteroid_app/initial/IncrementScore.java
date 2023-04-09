package asteroid_app.initial;

// A class that represents score incrementation in the game.
public class IncrementScore {
    private int score;

    // Constructor that initializes the score to 0.
    public IncrementScore() {
        this.score = 0;
    }

    // Method that returns the current score.
    public int getScore() {
        return score;
    }

    // Method that increments the score by the given amount.
    public void incrementScore(int amount) {
        score += amount;
    }
}
