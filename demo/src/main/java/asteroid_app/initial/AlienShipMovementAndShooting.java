package asteroid_app.initial;

import java.util.Random;

/*
 * public class AlienShipMovementAndShooting {
 * private double velocityX;
 * private double velocityY;
 * private long lastShotTime;
 * private long lastVelocityUpdateTime;
 * private static final long SHOT_INTERVAL = 1000; // Interval between shots in
 * milliseconds
 * private static final long VELOCITY_UPDATE_INTERVAL = 2000; // Interval
 * between velocity updates in milliseconds
 * private Random random = new Random();
 * 
 * public AlienShipMovementAndShooting(double velocityX, double velocityY) {
 * this.velocityX = velocityX;
 * this.velocityY = velocityY;
 * }
 * 
 * public void update(Alien alien, User_ship player, long now) {
 * alien.setTranslateX(alien.getTranslateX() + velocityX);
 * alien.setTranslateY(alien.getTranslateY() + velocityY);
 * 
 * if (now - lastShotTime > SHOT_INTERVAL * 1000000) { // Convert to nanoseconds
 * shoot(alien, player);
 * lastShotTime = now;
 * }
 * 
 * if (now - lastVelocityUpdateTime > VELOCITY_UPDATE_INTERVAL * 1000000) { //
 * Convert to nanoseconds
 * updateVelocity();
 * lastVelocityUpdateTime = now;
 * }
 * }
 * 
 * public void shoot(Alien alien, User_ship player) {
 * // Create bullet object and set its trajectory towards the player's current
 * position
 * // The implementation are missing
 * }
 * 
 * private void updateVelocity() {
 * // Update velocities randomly
 * double angle = random.nextDouble() * 2 * Math.PI;
 * double speed = 2;
 * 
 * velocityX = Math.cos(angle) * speed;
 * velocityY = Math.sin(angle) * speed;
 * }
 * }
 */
