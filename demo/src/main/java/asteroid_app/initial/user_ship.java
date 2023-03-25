package asteroid_app.initial;
import javafx.geometry.Point2D;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;

public class user_ship{
    //create a polygon object
    public Polygon u_ship=new Polygon(-10,-10,20,0,-10,10);
    public Point2D movement;
    
    // 
    public void move(){
        u_ship.setTranslateX(u_ship.getTranslateX()+movement.getX());
        u_ship.setTranslateX(u_ship.getTranslateX()+movement.getX());
    }
    
}