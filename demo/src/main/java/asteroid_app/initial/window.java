//this is the package name
package asteroid_app.initial;

// Application is the base class for all JavaFX applications
//need javafx to display the window
import javafx.application.Application;
// Stage is the top level JavaFX container
import javafx.stage.Stage;
// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.layout.Pane;
//import circle to draw a circle
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
//import polygon to draw a polygon
// Label for the text inside the window
import javafx.scene.control.Label;

import java.net.PortUnreachableException;
//Stuff for user_ship
import java.util.HashMap;

import javafx.scene.input.KeyCode;
import java.util.Map;
import javafx.animation.AnimationTimer;

//Stuff for Projectiles
import java.util.List;
import java.util.ArrayList;


//window extends the application class from javafx
public class window extends Application{

    //the window class overrides the start mehtod from the application class
    //takes a single parameter of type stage
    //inside the start method is where the User interface is created
    @Override
    public void start(Stage stage) throws Exception{
        // create a pane and set size
        Pane pane = new Pane();
        pane.setPrefSize(600, 400);
        // create label
        // create a scene
        Scene scene = new Scene(pane);

        Label label = new Label("This is how text is added to the screen.");
        pane.getChildren().add(label);


        //Object creation:
        // create the characters
        
        //as the level increase,add a for loop to increase asteroid 
        Polygon asteroid = Asteroid.createAsteroid();
        pane.getChildren().add(asteroid);
        
        Polygon alien = Alien.createAlien();
        pane.getChildren().add(alien);

        //Circle
        // create circle location from top left is 300x 200y and radius is 50
        Circle circle = new Circle(100, 100, 30);
        pane.getChildren().add(circle);

        //Ship
        //create a user_ship object
        user_ship ship=new user_ship();
        //set the location of the user_ship to center of the screen
        ship.u_ship.setTranslateX(300);
        ship.u_ship.setTranslateY(200);
        pane.getChildren().add(ship.u_ship);

        //Projectile
        // Declare a list of for projectiles, but leave it empty because we don'w want any projectile on screen
        // when the application start
        List<Projectile> projectiles = new ArrayList();

        //set the title of the window
        stage.setTitle("Asteroids");
        stage.setScene(scene);
        // Display the stage
        stage.show();


        //Key Presses:
        //create a hash map(key value pairs stored in a hash table) to store the key presses
        Map<KeyCode, Boolean> key_press=new HashMap<>();
        //add key pressed handler
        scene.setOnKeyPressed(event -> {key_press.put(event.getCode(), Boolean.TRUE);});
        //add key release handler
        scene.setOnKeyReleased(event -> {key_press.put(event.getCode(), Boolean.FALSE);});

        //Animation controls:
        //use an animation timer to update the screen
        new AnimationTimer(){
            @Override
            public void handle(long now){
                //if the left key is pressed
                if(key_press.getOrDefault(KeyCode.LEFT, false)){
                    //rotate the user_ship left
                    ship.u_ship.setRotate(ship.u_ship.getRotate()-5);
                }
                //if the right key is pressed
                if(key_press.getOrDefault(KeyCode.RIGHT, false)){
                    //rotate the user_ship right
                    ship.u_ship.setRotate(ship.u_ship.getRotate()+5);
                }
                //if the up key is pressed
                if(key_press.getOrDefault(KeyCode.UP, false)){
                    //move the user_ship forward
                    ship.u_ship.setTranslateX(ship.u_ship.getTranslateX()+Math.cos(Math.toRadians(ship.u_ship.getRotate())));
                    ship.u_ship.setTranslateY(ship.u_ship.getTranslateY()+Math.sin(Math.toRadians(ship.u_ship.getRotate())));
                }
                //if the space key is pressed: Create a projectile. Only keep 3 projectile in the projectiles list
                if (key_press.getOrDefault(KeyCode.SPACE,false) && projectiles.size() < 3){
                    // New projectile created has the same position as the ship
                    Projectile proj = new Projectile((int) ship.u_ship.getTranslateX(),(int) ship.u_ship.getTranslateY());

                    // Rotate like the ship
                    proj.getShape().setRotate(ship.u_ship.getRotate());

                    // Add the shooted projectile to the projectiles list 
                    projectiles.add(proj);

                    // Add the projectile shape to the screen
                    pane.getChildren().add(proj.getShape());
                    //    
                }
                // let the projectile move forward
                projectiles.forEach(proj -> proj.getShape().setTranslateX(proj.getShape().getTranslateX()+Math.cos(Math.toRadians(ship.u_ship.getRotate()))));
                projectiles.forEach(proj -> proj.getShape().setTranslateY(proj.getShape().getTranslateY()+Math.cos(Math.toRadians(ship.u_ship.getRotate()))));
                
            }
        }.start();
    }
//run the application
    public static void main(String[] args) {
        launch(args);
    }
}
