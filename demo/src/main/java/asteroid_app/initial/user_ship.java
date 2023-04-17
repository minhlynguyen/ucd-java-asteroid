package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

private Polygon u_ship;
    private Point2D position;

    public user_ship(double x, double y) {
        u_ship = new Polygon(-10, -10, 20, 0, -10, 10);
        position = new Point2D(x, y);
    }

    public Polygon getU_ship() {
        return u_ship;
    }

    public Point2D getPosition() {
        return position;
    }
    
}
