package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;
//import point 2d to represent the movement of the ship
import javafx.geometry.Point2D;

public class user_ship{
    //define a private polygon character to represnt the static ship
    // and a 2d point to represent the movement of the ship
    private Polygon ship;
    private Point2D movement;

    public user_ship(int x, int y){
        //create the user_ship shape
        this.ship = new Polygon(-5, 0, 5, 0, 0, -10);
        this.ship.setTranslateX(x);
        this.ship.setTranslateY(y);

        //initialize the movement of the ship to zero
        this.movement = new Point2D(0, 0);
    }

    //return the ship
    public Polygon getShip(){
        return this.ship;
    }
    // turn it left
    public void turnLeft(){
        this.ship.setRotate(this.ship.getRotate() - 5);
    }
    // turn it right
    public void turnRight(){
        this.ship.setRotate(this.ship.getRotate() + 5);
    }
    // accelerate the ship
    public void accelerate() {
        this.ship.setTranslateX(this.ship.getTranslateX() + this.movement.getX());
        this.ship.setTranslateY(this.ship.getTranslateY() + this.movement.getY());
    }

}