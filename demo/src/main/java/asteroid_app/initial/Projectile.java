package asteroid_app.initial;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Projectile extends Character{
    //1st way
    //create a polygon object
    // public Polygon projectile = new Polygon(2,-2,2,2,-2,2,-2,-2);
    // public Point2D movement = new Point2D(1, 0);
    //
    private Polygon shape;
    private Point2D movement;

    public Projectile(int x, int y){
        this.shape = new Polygon(2,-2,2,2,-2,2,-2,-2);
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        this.movement = new Point2D(0,0);
    }

    public Polygon getShape(){
        return shape;
    }
    
    public Point2D getMovement(){
        return movement;
    }

    public void move(){
        this.shape.setTranslateX(this.shape.getTranslateX()+movement.getX());
        this.shape.setTranslateX(this.shape.getTranslateY()+movement.getY());
    }
}