package asteroid_app.initial;

// A class that represents the movement and shooting behavior of an alien ship in the game.
public class AlienShipMovementAndShooting {
    private double velocityX;
    private double velocityY;
    private long lastShotTime;
    private long shotInterval = 1000; // Interval between shots in milliseconds

    // Constructor that initializes the AlienShipMovementAndShooting object with the given velocities.
    public AlienShipMovementAndShooting(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    // Method that updates the position and shooting behavior of the given Alien object.
    public void update(Alien alien, long now) {
        // Update the Alien object's position based on its velocities
        alien.setTranslateX(alien.getTranslateX() + velocityX);
        alien.setTranslateY(alien.getTranslateY() + velocityY);

        // Check if it's time for the Alien object to shoot
        if (now - lastShotTime > shotInterval * 1000000) { // Convert to nanoseconds
            shoot(alien);
            lastShotTime = now;
        }
    }

    // Method that handles shooting behavior for the given Alien object.
    public void shoot(Alien alien) {
        // Create bullet object and set its trajectory
        // The implementation details are missing and need to be added.
    }
}
