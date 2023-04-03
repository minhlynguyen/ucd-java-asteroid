package asteroid_app.initial;

//import polygon to draw a polygon
import javafx.scene.shape.Polygon;
//for hyperspace jump
import java.util.Random;

//The user ship class is a child of character
public class User_ship extends Character {

    // define a super constructor to create the ship
    // it inherits all the methods from Character class
    public User_ship(int x, int y) {
        // create the shape of the ship
        // each is an x,y co-ordinate for each point
        super(new Polygon(-10, 0, 0, -10, -10, -20, 20, -10), x, y);
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
    public void hyperspaceJump() {
        // Generate a random location within the window
        Random random = new Random();
        int newX = random.nextInt(window.WIDTH);
        int newY = random.nextInt(window.HEIGHT);
        // if these new co-ordinates collide with another object go again
        /*
         * if(newX == any value that currently has an asteroid){
         * find a newX
         * if(newY == any value that currently has an asteroid){
         * find a newY
         * }
         */
        // if the new location is safe to jump to
        // set the new co-ordinates
        this.setTranslateX(newX);
        this.setTranslateY(newY);
        // and set its movement to zero
        this.stopMovement();
    }
}
