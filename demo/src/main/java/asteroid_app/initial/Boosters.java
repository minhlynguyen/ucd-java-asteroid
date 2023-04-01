package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;

public class Boosters extends Character{

    public Boosters(int x, int y, double angle) {
        super(new Polygon(0,-20,-10,-10,-40,-20,-10,-30), x, y);
        this.getChar().setRotate(angle);
    }

    public void setFill(String color) {
        this.getChar().setFill(javafx.scene.paint.Color.web(color));
    }    
}
