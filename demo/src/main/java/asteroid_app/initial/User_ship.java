package asteroid_app.initial;

//import polygon to draw a polygon
import javafx.scene.shape.Polygon;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
//for hyperspace jump
import java.util.Random;

//The user ship class is a child of character
public class User_ship extends Character {

    private static final Polygon SHIP_SHAPE= new Polygon(-20, 20, 0, 0, -20, -20, 40, 0);

    // define a super constructor to create the ship
    // it inherits all the methods from Character class
    public User_ship(int x, int y) {
        // create the shape of the ship
        // each is an x,y co-ordinate for each point
        super(SHIP_SHAPE, x, y);
    }

    // set a new x position for the ship
    public void setTranslateX(int x) {
        // this ship, call method getChar from Character and
        // set the x co-ordinate
        this.getChar().setTranslateX(x);
    }

    // similarly for the y co-ordinate
    public void setTranslateY(int y) {
        this.getChar().setTranslateY(y);
    }

    // jump to a new location within the window
    public void hyperspaceJump(Pane pane) {
        boolean freeSpace = false;
        while (freeSpace == false) {
            Random random = new Random();
            //choose a random x,y co-ordinate within the window
            int newX = random.nextInt((int) pane.getWidth());
            int newY = random.nextInt((int) pane.getHeight());
            // Check for collisions within a 50 pixel radius around the new location
            boolean containsPolygon = false;
            // loop through all the nodes in the pane
            // each node is a polygon
            for (Node node : pane.getChildren()) {
                if (node instanceof Polygon) {
                    Polygon polygon = (Polygon) node;
                    // calculate the distance between the new location and the polygon
                    double distance = Math.sqrt(Math.pow(newX - polygon.getTranslateX(), 2) + Math.pow(newY - polygon.getTranslateY(), 2));
                    if (distance < 50 + polygon.getBoundsInLocal().getWidth() / 2) {
                        containsPolygon = true;
                        // get a new random location
                        break;
                    }
                }
            }
            // if the space is free, break out of the loop
            if (containsPolygon == false) {
                freeSpace = true;
                // set the new coordinates
                this.setTranslateX(newX);
                this.setTranslateY(newY);
                // and stop its movement
                this.stopMovement();
            }
        }
    }

    public Bullet fireBullet(){
                // the bullet appear in the screen
        // at the same coordinates as current coordinates of the ship
        // with same rotation angle
        Bullet bullet = new Bullet(this.getChar().getTranslateX(),this.getChar().getTranslateY());
        bullet.getChar().setRotate(this.getChar().getRotate());
        return bullet;
    }
}


