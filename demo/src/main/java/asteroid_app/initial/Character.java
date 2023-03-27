package src.main.java.asteroid_app.initial;

import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public class Character {

	private Polygon character;
	private Point2D movement;

	// Character constructor
	public Character(Polygon polygon, double x, double y){

		this.character = polygon;
		this.character.setTranslateX(x);
		this.character.setTranslateY(y);
		this.movement = new Point2D(0, 0);

	}

	
	// Get the character
	public Polygon getCharacter(){
		return character;
	}

	// The following moving distance is moved distance plus the current location
	// Split into x and y
	public void move(){
		this.character.setTranslateX(this.character.getTranslateX() + this.movement.getX());
		this.character.setTranslateY(this.character.getTranslateY() + this.movement.getY());
	}

	// Define speed on the x and y direction
	// The speed multiply 0.5 is enough to display
	public void speed(){
		double speedX = Math.cos(Math.toRadians(this.character.getRotate()));
		double speedY = Math.sin(Math.toRadians(this.character.getRotate()));

		this.movement = this.movement.add(speedX*0.5, speedY*0.5);

	}

	// To check if the objects collide, we check if they have common space polygon
	public Boolean collision(Character other){
		Shape collisionSpace = Shape.intersect(this.character, other.getCharacter());
		// If the width of the common space is >= 0, then they have collided
		if (collisionSpace.getBoundsInLocal().getWidth() >= 0){
			return true;
		}else{
			return false;
		}
	}

}
