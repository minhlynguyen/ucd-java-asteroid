package demo.src.main.java.asteroid_app.initial;

import java.util.Random;
import javafx.scene.shape.Polygon;

public class PolygonCreator {

    public Polygon createPolygon(int size) {

        Polygon polygon = new Polygon();

		double c1 = Math.cos(Math.PI / 3);
		double s1 = Math.sin(Math.PI / 3);
		double c2 = Math.cos(Math.PI / 6);
		double s2 = Math.sin(Math.PI / 6);

		Random rnd = new Random();
		// side is the length of polygon's one side
		double side = (20 + rnd.nextInt(10))*size;

		polygon.getPoints().addAll(
                0.0, 0.0,
				side * c1, -1 * side * s1,
				side * c2, -1 * side * s2,
				side * c2, side * s2,
				side * c1, side * s1,
				-1 * side * c1, side * s1,
				-1 * side * c2, -1 * side * s2,
				-1 * side * c1, -1 * side * s1);

        return polygon;
	}
}
