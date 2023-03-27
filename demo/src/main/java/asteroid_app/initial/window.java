//this is the package name
package src.main.java.asteroid_app.initial;

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
//import java.util.* to create a list for asteroids
import java.util.*;
//import javafx.scene.control.Button to display button
import javafx.scene.control.Button;

//Stuff for user_ship
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import java.util.Map;
import javafx.animation.AnimationTimer;


//window extends the application class from javafx
public class window extends Application {

    // the window class overrides the start mehtod from the application class
    // takes a single parameter of type stage
    // inside the start method is where the User interface is created
    @Override
    public void start(Stage stage) throws Exception {
        // create a pane and set size
        Pane pane = new Pane();
        pane.setPrefSize(1200, 800);
        // create label
        // create a scene
        Scene scene = new Scene(pane);

        Label label = new Label("This is how text is added to the screen.");
        pane.getChildren().add(label);

        // Object creation:
        // create the characters

        // Asteroid
        // At the beginning,  use a list to create several asteroid
	    List<Asteroid> asteroids = new ArrayList<>();
        for(int i=0; i<5; i++){
            double x = new Random().nextDouble()*1000;
            double y = new Random().nextDouble()*1000;
            Asteroid asteroid = new Asteroid(x, y, 3);
            asteroids.add(asteroid);
        }

        // Add these asteroids to Pane
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getCharacter())); 

        // as the level increase, add a for loop to increase asteroid
        // Polygon asteroid = Asteroid.createAsteroid();
        // pane.getChildren().add(asteroid);

        Polygon alien = Alien.createAlien();
        pane.getChildren().add(alien);

        // Circle
        // create circle location from top left is 300x 200y and radius is 50
        Circle circle = new Circle(100, 100, 30);
        pane.getChildren().add(circle);

        // Ship
        // create a user_ship object
        User_ship ship = new User_ship(600, 400);
        // set the location of the user_ship to center of the screen
        // ship.setTranslateX(600);
        // ship.setTranslateY(400);
        pane.getChildren().add(ship.getCharacter());





        // Construct a new pane to manage the controls
        Pane startMenuPane = new Pane();
        startMenuPane.setPrefSize(1200, 800);

        // Construct a new start menu scene
        Scene startMenuScene = new Scene(startMenuPane);

        // Labels on the screen
        // Label headline = new Label("ASTEROIDS");
        Button playGame = new Button("PLAY GAME");
        // Label highScores = new Label("HIGH SCORES");
        // Label website = new Label("www.freevideogamesonline.com");

        // Add labels into startMenuPane
        // startMenuPane.getChildren().add(headline);
        startMenuPane.getChildren().add(playGame);
        // startMenuPane.getChildren().add(highScores);
        // startMenuPane.getChildren().add(website);







        // set the title of the window
        stage.setTitle("Asteroids");
        stage.setScene(startMenuScene);

        // When click on play game button, enter the game play scene
        playGame.setOnAction(e -> stage.setScene(scene));

        // Display the stage
        stage.show();

        // Key Presses:
        // create a hash map(key value pairs stored in a hash table) to store the key
        // presses
        Map<KeyCode, Boolean> key_press = new HashMap<>();
        // add key pressed handler
        scene.setOnKeyPressed(event -> {
            key_press.put(event.getCode(), Boolean.TRUE);
        });
        // add key release handler
        scene.setOnKeyReleased(event -> {
            key_press.put(event.getCode(), Boolean.FALSE);
        });

        // Animation controls:
        // use an animation timer to update the screen
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // if the left key is pressed
                if (key_press.getOrDefault(KeyCode.LEFT, false)) {
                    // rotate the user_ship left
                    ship.getCharacter().setRotate(ship.getCharacter().getRotate() - 5);
                }
                // if the right key is pressed
                if (key_press.getOrDefault(KeyCode.RIGHT, false)) {
                    // rotate the user_ship right
                    ship.getCharacter().setRotate(ship.getCharacter().getRotate() + 5);
                }
                // if the up key is pressed
                if (key_press.getOrDefault(KeyCode.UP, false)) {
                    // move the user_ship forward
                    ship.getCharacter().setTranslateX(
                            ship.getCharacter().getTranslateX() + Math.cos(Math.toRadians(ship.getCharacter().getRotate())));
                    ship.getCharacter().setTranslateY(
                            ship.getCharacter().getTranslateY() + Math.sin(Math.toRadians(ship.getCharacter().getRotate())));
                }

                // Move the asteriods
                asteroids.forEach(asteroid -> asteroid.move());
                    
                // When the collision happens
                asteroids.forEach(asteroid -> {
                    if(asteroid.collision(ship)){

                        // Remove the collided asteroid from the pane and asteroids list when collision happens
                        pane.getChildren().remove(asteroid.getCharacter());
                        asteroids.remove(asteroid);

                        // Then add two new smaller asteroids on the scene and in the asteroids list
                        // If the size=3 asteroid is collided
                        if(asteroid.getInitialSize() == 3){
                            for(int i=0; i<2; i++){
                                Asteroid newAsteroid = new Asteroid(asteroid.getCharacter().getTranslateX(), asteroid.getCharacter().getTranslateY(), 2);
                                asteroids.add(newAsteroid);
                                pane.getChildren().add(newAsteroid.getCharacter());
                                newAsteroid.move();
                            }
                        }

                        // If the size=2 asteroid is collided:
                        if(asteroid.getInitialSize() == 2){
                            for(int i=0; i<2; i++){
                                Asteroid newAsteroid = new Asteroid(asteroid.getCharacter().getTranslateX(), asteroid.getCharacter().getTranslateY(), 1);
                                asteroids.add(newAsteroid);
                                pane.getChildren().add(newAsteroid.getCharacter());
                                newAsteroid.move();
                            }
                        }
                }});


            }
        }.start();
    }


    // run the application
    public static void main(String[] args) {
        launch(args);
    }
}
