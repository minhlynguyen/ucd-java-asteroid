package asteroid_app.initial;

import java.util.Random;
import javafx.scene.shape.Polygon;

public class PolygonCreator {

	public Polygon createPolygon(Size size) {

		Polygon polygon = new Polygon();

		double c1 = Math.cos(Math.PI / 3);
		double s1 = Math.sin(Math.PI / 3);
		double c2 = Math.cos(Math.PI / 6);
		double s2 = Math.sin(Math.PI / 6);

		Random rnd = new Random();

		switch (size) {
			case LARGE:{
				// side is the length of polygon's one side
				int side = (20 + rnd.nextInt(10)) * 3;
				polygon.getPoints().addAll(
						0.0, 0.0,
						side * c1, -1 * side * s1,
						side * c2, -1 * side * s2,
						side * c2, side * s2,
						side * c1, side * s1,
						-1 * side * c1, side * s1,
						-1 * side * c2, -1 * side * s2,
						-1 * side * c1, -1 * side * s1);
			}
				break;
			case MIDDLE: {
				int side = (20 + rnd.nextInt(10)) * 2;
				polygon.getPoints().addAll(
						0.0, 0.0,
						side * c1, -1 * side * s1,
						side * c2, -1 * side * s2,
						side * c2, side * s2,
						side * c1, side * s1,
						-1 * side * c1, side * s1,
						-1 * side * c2, -1 * side * s2,
						-1 * side * c1, -1 * side * s1);
			}
				break;
			case SMALL:{
				int side = (20 + rnd.nextInt(10));
				polygon.getPoints().addAll(
						0.0, 0.0,
						side * c1, -1 * side * s1,
						side * c2, -1 * side * s2,
						side * c2, side * s2,
						side * c1, side * s1,
						-1 * side * c1, side * s1,
						-1 * side * c2, -1 * side * s2,
						-1 * side * c1, -1 * side * s1);
			}
				break;
		}

		return polygon;

	}
}