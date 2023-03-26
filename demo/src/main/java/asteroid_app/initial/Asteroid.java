package asteroid_app.initial;

import java.util.Random;

import javafx.scene.shape.Polygon;

public class Asteroid extends Character{
	
	public static Polygon asteroid;
//	List<Asteroid> asteroids = new ArrayList<>();
   	
	public static Polygon createAsteroid(){
		Random rnd = new Random();
		//side is the length of polygon's one side
		double side = 50+rnd.nextInt(10);
		asteroid = new Polygon();
		
		double c1 = Math.cos(Math.PI/3);
		double s1 = Math.sin(Math.PI/3);
		double c2 = Math.cos(Math.PI/6);
		double s2 = Math.sin(Math.PI/6);
		
		asteroid.getPoints().addAll(
				0.0, 0.0,
				side*c1, -1*side*s1,
				side*c2, -1*side*s2,
				side*c2, side*s2,
				side*c1, side*s1,
				-1*side*c1, side*s1,
				-1*side*c2, -1*side*s2,
				-1*side*c1, -1*side*s1
				);
		

		initX =400;
		initY = 400;
		asteroid.setTranslateX(initX);
		asteroid.setTranslateY(initY);
		
		System.out.println(asteroid);
		return asteroid;			
	}

	public Asteroid(int x, int y){
		this.shape = asteroid;
		this.shape.setTranslateX(x);
		this.shape.setTranslateY(y);
	}
	
}
