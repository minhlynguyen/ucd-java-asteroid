package asteroid_app.initial;

import java.util.Random;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Character {

	public Polygon shape;
	private Shape collisionArea;
	public static double initX;
	public static double initY;
	
	public void Position(double initX, double initY){
		
//		Random rnd = new Random();
//		initX = rnd.nextDouble(200);
//		initY = rnd.nextDouble(200);
//		
//		this.initX = initX;
//		this.initY = initY;
	}

	
	public boolean collide(Character other) {
		collisionArea = Shape.intersect(this.shape, other.shape);
		return collisionArea.getBoundsInLocal().getWidth() != -1;
	}
}
