package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
//for hyperspace jump
import java.util.Random;


//Take my branch and get the movement working correvtly on the main
//The user ship class is a child of character
public class User_ship extends Character {

    //define a super constructor to create the ship
    //it inherits all the methods from Character class
    public User_ship(int x, int y) {
        // create the shape of the ship
        //each is an x,y co-ordinate for each point
        super(new Polygon(-20,20,0,0,-20,-20,40,0), x, y);
    }

    //set a new x position for the ship
    public void setTranslateX(int x) {
        // this ship, call method getChar from Character and 
        // set the x co-ordinate
        this.getChar().setTranslateX(x);
    }

    //similarly for the y co-ordinate
    public void setTranslateY(int y) {
        this.getChar().setTranslateY(y);
    }

    //jump to a new location within the window
    public void hyperspaceJump(Pane pane) {

        boolean freeSpace=false;
        while (freeSpace==false){
            Random random = new Random();
            int newX = random.nextInt((int) pane.getWidth());
            int newY = random.nextInt((int) pane.getHeight());
            // Check for collisions within a 5 pixel radius around the new location
            boolean containsPolygon = false;
            for (Node node : pane.getChildren()) {
                if (node instanceof Polygon) {
                    Polygon polygon = (Polygon) node;
                    double distance = Math.sqrt(Math.pow(newX - polygon.getTranslateX(), 2) + Math.pow(newY - polygon.getTranslateY(), 2));
                    if (distance < 50 + polygon.getBoundsInLocal().getWidth() / 2) {
                        containsPolygon = true;
                        //get a new random location
                        break;
                    }
                }
            }
            //if the space is free, break out of the loop
            if (containsPolygon == false) {
                freeSpace = true;
                //set the new coordinates
                this.setTranslateX(newX);
                this.setTranslateY(newY);
                //and stop its movement
                this.stopMovement();
            }
        }
    }
}


