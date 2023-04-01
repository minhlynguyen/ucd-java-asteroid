package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;

public class Boosters extends Character{

    ////maybe need to create them as a group!!!!!!!!

    public Boosters(int x, int y, double angle) {
        super(new Polygon(0,0,-10,10,-40,0,-10,-10), x, y);
        this.getChar().setRotate(angle);
        //offset it so it is behind the ship regardless of the current orientation


    }

    public void setFill(String color) {
        this.getChar().setFill(javafx.scene.paint.Color.web(color));
    }    
}
