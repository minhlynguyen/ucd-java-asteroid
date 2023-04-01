package asteroid_app.initial;
public class IncrementScore {
    private int score;

    public IncrementScore() {
        score = 0;
    }

    public void addPoints(int points, PlayerLives playerLives) {
        int oldScore = score;
        score += points;

        if (oldScore / 10000 < score / 10000) {
            playerLives.gainLife();
        }
    }

    public int getScore() {
        return score;
    }
}
