package asteroid_app.initial;

import java.util.Random;

public class Asteroid extends Character {

	private double movingAngle;
	private int initialsize;

	// Except variables x,y inherited from character, asteroid also needs to set its
	// size
	public Asteroid(double x, double y, int size) {

		// Use PolygonCreator class to create an asteroid polygon
		super(new PolygonCreator().createPolygon(size), x, y);

		// Only asteroid needs to set an initial angle, which can use initialAngle to
		// express
		// Because the direction is random, so we use Random() to generate an angle
		Random rnd = new Random();

		// The max angle is 360
		// We import setRotate method to set its initial angel
		super.getChar().setRotate(rnd.nextDouble() * 360);

		// Generate a fixed moving angle
		this.movingAngle = 0.5 - rnd.nextDouble();

		this.initialsize = size;

		//increase their initial speed by accelerating upon creation
		//increase this as levels increase?
		for(int i=0; i<rnd.nextInt(150); i++){
			this.accelerate();
		}

	}

	// Asteroid has a random moving direction, so we need to set a override part in
	// move() method
	@Override
	public void move() {
		super.move();
		super.getChar().setRotate(super.getChar().getRotate() + movingAngle);
	}

	public int getInitialSize() {
		return initialsize;
	}

}
