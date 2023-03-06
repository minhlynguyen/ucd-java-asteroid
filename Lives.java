import java.util.Scanner;

/**
 * A class that represents the lives of a player in a game.
 */
public class Lives {
    private int lives;

    /**
     * Initializes the Lives object with 3 lives.
     */
    public Lives() {
        this.lives = 3; // Default value is 3
    }

    /**
     * Decrements the number of lives by 1.
     * return true if the player still has lives left, false otherwise.
     */
    public boolean loseLife() {
        this.lives--;
        if (this.lives <= 0) {
            return false; // No lives left
        }
        return true; // Lives left
    }

    /**
     * Increments the number of lives by 1.
     */
    public void gainLife() {
        this.lives++;
    }

    /**
     * Gets the number of lives.
     * return the number of lives.
     */
    public int getLives() {
        return this.lives;
    }
}

/**
 * A class that represents the score system of a game.
 */
public class ScoreSystem {
    private int score;
    private Lives lives;

    /**
     * Initializes the ScoreSystem object with a score of 0 and 3 lives.
     */
    public ScoreSystem() {
        this.score = 0;
        this.lives = new Lives();
    }

    /**
     * Adds the given number of points to the score.
     * If the score reaches or exceeds 10000, a life is gained.
     * param points the number of points to add to the score.
     */
    public void addScore(int points) {
        this.score += points;
        if (this.score >= 10000) {
            this.score -= 10000;
            this.lives.gainLife(); // Gain a life
        }
    }

    /**
     * Gets the current score.
     * return the current score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the number of lives.
     * return the number of lives.
     */
    public int getLives() {
        return this.lives.getLives();
    }
}

/**
 * A class that represents an Asteroids game.
 */
public class AsteroidsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Initialize the gameOver flag to false
        boolean gameOver = false;
        // Create a new ScoreSystem object to keep track of the score and lives
        ScoreSystem scoreSystem = new ScoreSystem();

        while (!gameOver) {
            // Process score
            scoreSystem.addScore(100);

            // Check if player lost a life
            if (!scoreSystem.loseLife()) {
                // If the player has lost all lives, end the game and print the final score
                System.out.println("GAME OVER");
                System.out.println("Score: " + scoreSystem.getScore());
                gameOver = true;
                break;
            }
            // Print the current score and live
            System.out.println("Score: " + scoreSystem.getScore());
            System.out.println("Lives: " + scoreSystem.getLives());
            // Prompt the user to press enter to continue
            System.out.println("Press enter to continue...");

            scanner.nextLine();
        }
    }
}
