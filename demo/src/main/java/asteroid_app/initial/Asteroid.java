package asteroid_app.initial;

import java.util.Random;
import java.util.List;
import javafx.scene.layout.Pane;

public class Asteroid extends Character {

	private double movingAngle;
	private Size initialsize;

	// Except variables x,y inherited from character, asteroid also needs to set its
	// size
	public Asteroid(double x, double y, Size size) {

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
			switch(size){
				case LARGE:
					this.accelerate(0.005);
					break;
				case MIDDLE:
					this.accelerate(0.05);
					break;
				case SMALL:
					this.accelerate(0.5);
					break;
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

	public Size getInitialSize() {
		return initialsize;
	}

	// function of asteroids split
	public static void asteroidSplit(Asteroid asteroid, List<Asteroid> asteroids, Pane pane) {
        // Then add two new smaller asteroids on the scene and in the asteroids list
        // If the large size asteroid is collided
        if (asteroid.getInitialSize() == Size.LARGE) {
            // add new middle size asteroids
            for (int i = 0; i < 2; i++) {
                Asteroid newAsteroid = new Asteroid(
                        asteroid.getChar().getTranslateX(),
                        asteroid.getChar().getTranslateY(), Size.MIDDLE);
                asteroids.add(newAsteroid);
                pane.getChildren().add(newAsteroid.getChar());
                newAsteroid.move();
                // remove the collided asteroid
                pane.getChildren().remove(asteroid.getChar());
                asteroids.remove(asteroid);
            }
        }

        // If the size=2 asteroid is collided:
        else if (asteroid.getInitialSize() == Size.MIDDLE) {
            for (int i = 0; i < 2; i++) {
                // add new small size asteroids
                Asteroid newAsteroid = new Asteroid(
                        asteroid.getChar().getTranslateX(),
                        asteroid.getChar().getTranslateY(), Size.SMALL);
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