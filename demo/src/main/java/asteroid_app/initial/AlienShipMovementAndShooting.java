package asteroid_app.initial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienShipMovementAndShooting {
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


    private AlienShipCreation alienShip;
    private int playerScore = 0;
    private static final int SCREEN_MAX_X = 800;
    private static final int SCREEN_MAX_Y = 600;

    public AlienShipMovementAndShooting(double velocityX, double velocityY, AlienShipCreation alienShipCreation, PlayerLives player) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.alienShip = alienShipCreation.getAlienShip();
        this.randomDirection = random.nextDouble() * 2 * Math.PI;
    }
   public AlienShipMovementAndShooting(double velocityX, double velocityY, AlienShipCreation alienShipCreation, PlayerLives player) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.alienShip = alienShipCreation.getAlienShip();
        this.randomDirection = random.nextDouble() * 2 * Math.PI;
    }

        moveAlienShip();
    }
 private void updateVelocity() {
        AlienShip alienShip = this.alienShip;
        double speed = alienShip.getSpeed();
        velocityX = speed * Math.cos(randomDirection);
        velocityY = speed * Math.sin(randomDirection);
    }


    public void moveAlienShip() {
        if (!alienShip.isActive()) {
            alienShip.decrementSpawnTimer();
            if (alienShip.getSpawnTimer() <= 0) {
                spawnAlienShip();
            }
        } else {
            // move alien
            alienShip.updatePosition(velocityX, velocityY);
            double xPos = alienShip.getX();
            wrapPosition(alienShip);
            // don't wrap x position
            alienShip.setX(xPos);

            if (alienShip.isLeftToRight()) {
                // left to right
                if (alienShip.getX() > SCREEN_MAX_X) {
                    endAlienShip();
                }
            } else {
                // right to left
                if (alienShip.getX() < 0) {
                    endAlienShip();
                }
            }

            alienShip.decrementChangeDirectionTimer();
            if (alienShip.getChangeDirectionTimer() <= 0) {
                // change direction
                double direction = Math.random() * Math.PI * 5 / 6 - (Math.PI * 5 / 12);
                if (!alienShip.isLeftToRight()) {
                    // right to left
                    direction += Math.PI;
                }
                direction = keepAngleInRange(direction);
                alienShip.setVelocityDirection(direction);
                alienShip.setRotation(direction);
                // reset timer
                alienShip.resetChangeDirectionTimer();
            }

            if (alienShip.getBulletCount() < MAX_ALIEN_BULLETS) {
                fireAlienBullet(player);
            }
        }
    }


}
