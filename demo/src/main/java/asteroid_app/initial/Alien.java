package demo.src.main.java.asteroid_app.initial;

import java.util.Random;

import javafx.scene.shape.Polygon;
 
public class Alien extends Character{

	public Alien(int x, int y) {
        // create the shape of the ship
        //each is an x,y co-ordinate for each point
        super(new Polygon(0.0, 0.0,
		5.0, 0.0,
		10.0, 5.0,
		20.0, 10.0,
		10.0, 15.0,
		-5.0, 15.0,
		-15.0, 10.0,
		-5.0, 5.0
		), x, y);
    }

	public Alien createAlienShip(int x, int y) {
        Alien alien = new Alien(x,y); // Create a new Alien object
        alien.setTranslateX(x); // Set the x coordinate
        alien.setTranslateY(y); // Set the y coordinate
        return alien; // Return the created Alien object
    }

	 //set a new x position for the alien
	 public void setTranslateX(int x) {
        // this ship, call method getChar from Character and 
        // set the x co-ordinate
        this.getChar().setTranslateX(x);
    }


	//similarly for the y co-ordinate
    public void setTranslateY(int y) {
        this.getChar().setTranslateY(y);
    }
	
//	public void size() {
//		int middleSize = 5;
//		int superSize = 10;
//		this.x *= middleSize;
//		this.y *= superSize;
//	}
	
	public void multiply() {

	}
///Alien ship movement and shooting

	private double velocityX;
	private double velocityY;
	private long lastShotTime;
	private long lastVelocityUpdateTime;
	private long shotInterval = 1000; // Interval between shots in milliseconds
	private long velocityUpdateInterval = 2000; // Interval between velocity updates in milliseconds
	private Random random = new Random();

	public void AlienShipMovementAndShooting(double velocityX, double velocityY) {
		this.velocityX = velocityX;
		this.velocityY = velocityY;
	}

	public void update(Alien alien, User_ship player, long now) {
		alien.move();

		if (now - lastShotTime > shotInterval * 1000000) { // Convert to nanoseconds
			shoot(alien, player);
			lastShotTime = now;
		}

		if (now - lastVelocityUpdateTime > velocityUpdateInterval * 1000000) { // Convert to nanoseconds
			updateVelocity();
			lastVelocityUpdateTime = now;
		}
	}

	public void shoot(Alien alien, User_ship player) {
		// Create bullet object and set its trajectory towards the player's current position
		// The implementation are missing 
	}

	private void updateVelocity() {
		// Update velocities randomly
		double angle = random.nextDouble() * 2 * Math.PI;
		double speed = 2; 

		velocityX = Math.cos(angle) * speed;
		velocityY = Math.sin(angle) * speed;
	}





}
