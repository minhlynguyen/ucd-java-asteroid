package asteroid_app.initial;

import javafx.geometry.Point2D;

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

    private AlienShip alienShip;
    private int playerScore = 0;
    private static final int SCREEN_MAX_X = 800;
    private static final int SCREEN_MAX_Y = 600;

    public AlienShipMovementAndShooting(double velocityX, double velocityY, AlienShip alienShip, PlayerLives player) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.alienShip = alienShip;
        this.randomDirection = random.nextDouble() * 2 * Math.PI;
    }

    private void updateVelocity() {
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
            alienShip.move(velocityX, velocityY);
            double xPos = alienShip.getPosition().getX();
            wrapPosition(alienShip);
            // don't wrap x position
            alienShip.setPosition(new Point2D(xPos, alienShip.getPosition().getY()));

            if (alienShip.isLeftToRight()) {
                // left to right
                if (alienShip.getPosition().getX() > SCREEN_MAX_X) {
                    endAlienShip();
                }
            } else {
                // right to left
                if (alienShip.getPosition().getX() < 0) {
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
                fireAlienBullet(alienShip, player);
            }
        }
    }

    private void spawnAlienShip() {
        alienShip.setActive(true);
        alienShip.setSpawnTimer(0);
        double randomY = random.nextDouble() * SCREEN_MAX_Y;
        alienShip.setPosition(new Point2D(0, randomY));
        alienShip.setLeftToRight(true);
        alienShip.resetChangeDirectionTimer();
    }

