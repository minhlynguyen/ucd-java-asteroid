package asteroid_app.initial;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Projectile extends Character{
    //create a polygon object
    public Polygon projectile = new Polygon(2,-2,2,2,-2,2,-2,-2);
    public Point2D movement = new Point2D(1, 0);
    
    // 
    public void move(){
        projectile.setTranslateX(projectile.getTranslateX()+movement.getX());
        projectile.setTranslateX(projectile.getTranslateX()+movement.getX());
    }
}