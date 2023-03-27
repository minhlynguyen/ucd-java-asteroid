package src.main.java.asteroid_app.initial;

//import polygon to draw a polygon
import javafx.scene.shape.Polygon;

public class User_ship extends Character{

    //Create a private object

    public User_ship(double x, double y){
        
        // create a polygon object
        super(new Polygon(-10, -10, 20, 0, -10, 10), x, y);
    }

}