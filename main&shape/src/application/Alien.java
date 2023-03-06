package application;

import java.util.Random;

import javafx.scene.shape.Polygon;

public class Alien extends Character{

	public static Polygon createAlien() {
		
		// small size of alien
		Polygon alien = new Polygon();
		alien.getPoints().addAll(
				0.0, 0.0,
				5.0, 0.0,
				10.0, 5.0,
				20.0, 10.0,
				10.0, 15.0,
				-5.0, 15.0,
				-15.0, 10.0,
				-5.0, 5.0
				);
		
		// initial the position of the alien
		Random rnd = new Random();
		
		initX = rnd.nextDouble(600);
		initY = rnd.nextDouble(600);
		alien.setTranslateX(initX);
		alien.setTranslateY(initY);
		
		System.out.println(alien);
		
		return alien;
	} 
	
//	public void size() {
//		int middleSize = 5;
//		int superSize = 10;
//		this.x *= middleSize;
//		this.y *= superSize;
//	}
	
	public void multiply() {

	}
}
