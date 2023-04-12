package demo.src.main.java.asteroid_app.initial;

import java.util.Random;

/*
 * public class Alien extends Character {
 * private int screenWidth;
 * private int screenHeight;
 * private Random random;
 * private int shotInterval = 2000;
 * private int timeSinceLastShot = 0;
 * 
 * public AlienShipCreation() {
 * this.screenWidth = window.WIDTH;
 * this.screenHeight = window.HEIGHT;
 * random = new Random();
 * }
 * 
 * public Alien createAlienShip() {
 * int x, y, dx, dy;
 * do {
 * x = random.nextInt(screenWidth);
 * y = random.nextInt(screenHeight);
 * } while (x > screenWidth / 3 && x < screenWidth * 2 / 3 && y > screenHeight /
 * 3 && y < screenHeight * 2 / 3);
 * // The above loop ensures that the alien ship does not spawn too close to the
 * player's starting position
 * 
 * // Randomize the direction of movement
 * double angle = random.nextDouble() * 2 * Math.PI;
 * dx = (int) Math.round(10 * Math.cos(angle));
 * dy = (int) Math.round(10 * Math.sin(angle));
 * 
 * return new Alien(new Point(x, y), new Vector(dx, dy));
 * }
 * 
 * public void update(int delta, AlienShip alienShip, PlayerShip playerShip,
 * BulletCreation bulletCreation) {
 * timeSinceLastShot += delta;
 * if (timeSinceLastShot >= shotInterval) {
 * shoot(alienShip, playerShip, bulletCreation);
 * timeSinceLastShot = 0;
 * }
 * 
 * // Randomize the direction of movement every 200 updates
 * if (random.nextInt(200) == 0) {
 * double angle = random.nextDouble() * 2 * Math.PI;
 * alienShip.setVelocity(new Vector(Math.cos(angle) * AlienShip.SPEED,
 * Math.sin(angle) * AlienShip.SPEED));
 * }
 * 
 * // Move the alien ship
 * alienShip.move(delta);
 * 
 * // Check if the alien ship has gone off the screen and reposition it if
 * necessary
 * if (alienShip.getPosition().x < -AlienShip.RADIUS) {
 * alienShip.getPosition().x = screenWidth + AlienShip.RADIUS;
 * } else if (alienShip.getPosition().x > screenWidth + AlienShip.RADIUS) {
 * alienShip.getPosition().x = -AlienShip.RADIUS;
 * }
 * 
 * if (alienShip.getPosition().y < -AlienShip.RADIUS) {
 * alienShip.getPosition().y = screenHeight + AlienShip.RADIUS;
 * } else if (alienShip.getPosition().y > screenHeight + AlienShip.RADIUS) {
 * alienShip.getPosition().y = -AlienShip.RADIUS;
 * }
 * }
 * 
 * private void shoot(Alien alienShip, User_ship playerShip, Bullet
 * bulletCreation) {
 * Vector direction = new Vector(playerShip.getPosition().x -
 * alienShip.getPosition().x, playerShip.getPosition().y -
 * alienShip.getPosition().y);
 * direction.normalize();
 * bulletCreation.createBullet(alienShip.getPosition(), direction, true);
 * }
 * }
 */
