package asteroid_app.initial;
//import polygon to draw a polygon
import javafx.scene.shape.Polygon;
//for hyperspace jump
import java.util.Random;


public class User_ship extends Character {
    
    //private static final int jumpRange = 5;

    public User_ship(int x, int y) {
        super(new Polygon(-10, 0, -10, -20, 20, -10), x, y);
    }

    public void setTranslateX(int x) {
        this.getChar().setTranslateX(x);
    }

    public void setTranslateY(int y) {
        this.getChar().setTranslateY(y);
    }

    public void hyperspaceJump() {
        // Generate a random location within the hyperspace jump range
        Random random = new Random();

        int newX = random.nextInt(window.WIDTH);
        int newY = random.nextInt(window.HEIGHT);
        /*
        if(newX == any value that currently has an asteroid){
            find a newX
        if(newY == any value that currently has an asteroid){
            find a newY
        }*/

        this.setTranslateX(newX);
        this.setTranslateY(newY);
        
    }
}

