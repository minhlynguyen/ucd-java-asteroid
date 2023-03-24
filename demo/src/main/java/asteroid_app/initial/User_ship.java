package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;

public class User_ship extends Character{
        
    public User_ship(int x, int y){
        super(new Polygon(-10,0,-10,-20,20,-10), x, y);
    }
}
