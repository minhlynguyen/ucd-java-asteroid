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


//Stuff for user_ship
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import java.util.Map;
import javafx.animation.AnimationTimer;


//window extends the application class from javafx
public class window extends Application{

    //define the size of the screen can be accessed by all classes
    public static int WIDTH = 800;
    public static int HEIGHT = 600;
    //the window class overrides the start mehtod from the application class
    //takes a single parameter of type stage
    //inside the start method is where the User interface is created
    @Override
    public void start(Stage stage) throws Exception{
        // create a pane and set size
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);
        // create label
        // create a scene
        Scene scene = new Scene(pane);

        Label label = new Label("This is how text is added to the screen.");
        pane.getChildren().add(label);


        //Object creation:
                // create the characters
        
        //as the level increase,add a for loop to increase asteroid 
        //Polygon asteroid = Asteroid.createAsteroid();
        //pane.getChildren().add(asteroid);
        
        //Polygon alien = Alien.createAlien();
        //pane.getChildren().add(alien);



        //Circle
        // create circle location from top left is 300x 200y and radius is 50
        Circle circle = new Circle(100, 100, 30);
        pane.getChildren().add(circle);

        //Ship
        //create a user_ship object and initialize location
        User_ship ship = new User_ship(WIDTH/2, HEIGHT/2);
        //add the user_ship to the pane
        pane.getChildren().add(ship.getChar());


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
            //check if j key was pressed so we dont repeatedly go into hyperspace
            //inserted here to prevent multiple jumps
            boolean jPress = false;

            @Override
            public void handle(long now){
                //if the left key is pressed
                if(key_press.getOrDefault(KeyCode.LEFT, false)){
                    //rotate the user_ship left
                    ship.turnLeft();
                }
                //if the right key is pressed
                if(key_press.getOrDefault(KeyCode.RIGHT, false)){
                    //rotate the user_ship right
                    ship.turnRight();
                }
                // what does this do?
                ship.move();
                //if the up key is pressed
                if(key_press.getOrDefault(KeyCode.UP, false)){
                    //accelerate the user_ship
                    ship.accelerate();
                }
                
                // if the J key is pressed for jump and has not already jumped
                if (key_press.getOrDefault(KeyCode.J, false) && jPress==false) {
                    //jump to a new location and if successful set flag to true
                    ship.hyperspaceJump();
                    jPress = true;                   
                }
                // if the J key is released
                if (!key_press.getOrDefault(KeyCode.J, false)) {
                    jPress = false; // reset the flag
                }

            }
        }.start();
    }
//run the application
    public static void main(String[] args) {
        launch(args);
    }
}
