package asteroid_app.initial;

import asteroid_app.custom.AlienShip;
import javafx.geometry.Point2D;

import java.util.Random;

public class AlienShipMovementAndShooting {
    private AlienShip alienShip;
    private double velocityX;
    private double velocityY;
    private long lastShotTime;
    private long lastVelocityUpdateTime;
    private static final long SHOT_INTERVAL = 1000; // Interval between shots in milliseconds
    private static final long VELOCITY_UPDATE_INTERVAL = 2000; // Interval between velocity updates in milliseconds
    private static final int MAX_ALIEN_BULLETS = 10;
    private Random random = new Random();
    private double randomDirection;
    private static final double MAX_DIRECTION_CHANGE = Math.PI / 6; // Maximum direction change in radians
    private static final int SCREEN_MAX_X = 800;
    private static final int SCREEN_MAX_Y = 600;

    private user_ship userShip;

    public AlienShipMovementAndShooting(AlienShip alienShip, user_ship userShip) {
        this.alienShip = alienShip;
        this.userShip = userShip;
        this.randomDirection = random.nextDouble() * 2 * Math.PI;
        updateVelocity();
    }

    private void updateVelocity() {
        double speed = alienShip.getSpeed();
        velocityX = speed * Math.cos(randomDirection);
        velocityY = speed * Math.sin(randomDirection);
    }

    public void moveAlienShip() {
        if (alienShip.isActive()) {
            // move alien
            Point2D newPosition = alienShip.getPosition().add(velocityX, velocityY);
            alienShip.setPosition(newPosition);

            // Wrap the position around the screen edges
            double newX = (newPosition.getX() + SCREEN_MAX_X) % SCREEN_MAX_X;
            double newY = (newPosition.getY() + SCREEN_MAX_Y) % SCREEN_MAX_Y;
            alienShip.setPosition(new Point2D(newX, newY));

            // Update velocity periodically
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastVelocityUpdateTime >= VELOCITY_UPDATE_INTERVAL) {
                lastVelocityUpdateTime = currentTime;
                randomDirection = (randomDirection + random.nextDouble() * MAX_DIRECTION_CHANGE) % (2 * Math.PI);
                updateVelocity();
            }
            
            long currentTime = System.currentTimeMillis();
            if (alienShip.getBulletCount() < MAX_ALIEN_BULLETS && currentTime - lastShotTime >= SHOT_INTERVAL) {
            lastShotTime = currentTime;
            fireAlienBullet(alienShip, userShip);
        }

           
        }

     private void fireAlienBullet(AlienShip alienShip, user_ship userShip) {
        Point2D alienShipPosition = alienShip.getPosition();
        Point2D userShipPosition = userShip.getPosition();
        Point2D direction = userShipPosition.subtract(alienShipPosition).normalize();

        // Create a bullet and set its position
        Bullet bullet = new Bullet((int) alienShipPosition.getX(), (int) alienShipPosition.getY());


        // Increment the alien ship's bullet count
        alienShip.incrementBulletCount();
    }
}
