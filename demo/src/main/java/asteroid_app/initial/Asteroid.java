package asteroid_app.initial;

import java.util.Random;
import java.util.List;
import javafx.scene.layout.Pane;

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

		// increase their initial speed by accelerating upon creation
		// increase this as levels increase?
		for (int i = 0; i < rnd.nextInt(150); i++) {
			if (size == 3) {
				this.accelerate(0.005);
			} else if (size == 2) {
				this.accelerate(0.01);
			} else {
				this.accelerate(0.5);
			}
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

	// function of asteroids split
	public static void asteroidSplit(Asteroid asteroid, List<Asteroid> asteroids, Pane pane) {
        // Then add two new smaller asteroids on the scene and in the asteroids list
        // If the size=3 asteroid is collided
        if (asteroid.getInitialSize() == 3) {
            // add new size 2 asteroids
            for (int i = 0; i < 2; i++) {
                Asteroid newAsteroid = new Asteroid(
                        asteroid.getChar().getTranslateX(),
                        asteroid.getChar().getTranslateY(), 2);
                asteroids.add(newAsteroid);
                pane.getChildren().add(newAsteroid.getChar());
                newAsteroid.move();
                // remove the collided asteroid
                pane.getChildren().remove(asteroid.getChar());
                asteroids.remove(asteroid);
            }
        }

        // If the size=2 asteroid is collided:
        else if (asteroid.getInitialSize() == 2) {
            for (int i = 0; i < 2; i++) {
                // add new size 1 asteroids
                Asteroid newAsteroid = new Asteroid(
                        asteroid.getChar().getTranslateX(),
                        asteroid.getChar().getTranslateY(), 1);
                asteroids.add(newAsteroid);
                pane.getChildren().add(newAsteroid.getChar());
                newAsteroid.move();
                // remove the collided asteroid
                pane.getChildren().remove(asteroid.getChar());
                asteroids.remove(asteroid);
            }
        }

        // if the size=1 asteroid is collided
        else {
            // remove the collided asteroid
            pane.getChildren().remove(asteroid.getChar());
            asteroids.remove(asteroid);
        }
    }

}
