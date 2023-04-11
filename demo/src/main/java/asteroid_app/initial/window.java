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
import javafx.scene.text.Text;
import javafx.scene.control.Label;
//import java.util.* to create a list for asteroids
import java.util.*;
//import javafx.scene.control.Button to display button
import javafx.scene.control.Button;
//import VBox and HBox to manage the nodes in a pane
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
//import Font to set the size of labels
import javafx.scene.text.Font;
//import Fontweight to set the style of labels and buttons
import javafx.scene.text.FontWeight;
//import BorderPanet to set the position of the nodes
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;

//Stuff for keypresses
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;

//window extends the application class from javafx
public class window extends Application {

    //define the size of the screen can be accessed by all classes
    public static int WIDTH = 1400;
    public static int HEIGHT = 1000;
    //the window class overrides the start mehtod from the application class
    //takes a single parameter of type stage
    //inside the start method is where the User interface is created
    public static int pointX = WIDTH / 2 - 7;
    public static int pointY = 20;

    @Override
    public void start(Stage stage) throws Exception {

        // Create the game scences
        // MAIN SCENE: create a pane and set size
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);

        // create a scene and label
        Scene mainScene = new Scene(pane);

        // display the points
        Text pointText = new Text(pointX, pointY, "Points: 0");
        pane.getChildren().add(pointText);

        // calculate the point
        AtomicInteger points = new AtomicInteger();

        // Object creation:
        // create the characters

        // Asteroid
        // At the beginning,  use a list to create several asteroid
	    List<Asteroid> asteroids = new ArrayList<>();
        for(int i=0; i<10; i++){
            double x = new Random().nextDouble()*1000;
            double y = new Random().nextDouble()*1000;
            Asteroid asteroid = new Asteroid(x, y, 3);
            asteroids.add(asteroid);
        }

        // Add these asteroids to Pane
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getChar()));

        // as the level increase, add a for loop to increase asteroid
        // Polygon asteroid = Asteroid.createAsteroid();
        // pane.getChildren().add(asteroid);

        //Alien

        // Ship
        // create a user_ship object and initialize location
        User_ship ship = new User_ship(WIDTH / 2, HEIGHT / 2);
        // add the user_ship to the pane
        pane.getChildren().add(ship.getChar());

        // Bullet
        List<Bullet> bullets = new ArrayList<>();

        // QuitGame button
        Button quitGame = new Button("QUIT");
        Button restartGame = new Button("RESTART");

        // Control box
        HBox controlBox = new HBox(10, quitGame, restartGame);
        controlBox.setAlignment(Pos.CENTER);
        pane.getChildren().add(controlBox);
        controlBox.setTranslateX(WIDTH*0.9);
        controlBox.setTranslateY(HEIGHT*0.01);

        // Start Menu
        // set a border pane to ocntain and set the position of nodes
        BorderPane borderPane = new BorderPane();

        // create a new scene
        Scene startMenuScene = new Scene(borderPane, WIDTH, HEIGHT);

        // labels on the screen
        Label headline = new Label("ASTEROIDS");
        headline.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        Button playGame = new Button("PLAY GAME");
        Button highScores = new Button("HIGH SCORES");
        Label website = new Label("www.freevideogamesonline.com");

        // create a Vbox to manage the nodes on the start menu
        VBox vBox = new VBox(30, headline, playGame, highScores, website);

        // set the position of vBox
        vBox.setAlignment(Pos.CENTER);

        // add vBox into root
        borderPane.setCenter(vBox);

        // ------ GAME OVER SCENE ------
        // GridPane gridPane = new GridPane();
        // gridPane.setAlignment(Pos.BASELINE-CENTER);
        // gridPane.setHgap(10);
        // gridPane.setVgap(30);
        // gridPane.setPadding(new Insets(0, 10, 0, 10));

        // Label nameField = new Label("Enter your name:");
        TextField nameText = new TextField();
        nameText.setPrefWidth(100);
        // gridPane.add(nameField, 0, 0);
        // gridPane.add(nameText, 1, 0);
        
        Label headlineover = new Label("GAME OVER");
        headlineover.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
        // Button playGameSm = new Button("PLAY GAME");
        Label yourScore = new Label("Your score is");
        
        // Score update later
        int topTenMin = 4000;
        int finalScore = 5000;
        
        Label score = new Label(""+finalScore);
        Label yourName = new Label("Enter your name");

        Button saveScore = new Button("Save");
        Button seeHighScore = new Button("High Scores");
        
        VBox infoBox;
        
        
        if(finalScore >= topTenMin){
            infoBox = new VBox(25, headlineover, yourScore, score, yourName, nameText, saveScore);
        }else{
            infoBox = new VBox(25, headlineover, yourScore, score, seeHighScore);
        }
        
        infoBox.setAlignment(Pos.CENTER);
        Scene gameOverScene= new Scene(infoBox,WIDTH*0.6,HEIGHT*0.8);

        // ------ SWITCHING BETWEEN SCENEs ------
        // set the title of the window
        stage.setTitle("Group 11-Asteroids Game");
        stage.setScene(startMenuScene);
 
        // when click on play game button, enter the game play scene
        playGame.setOnAction(e -> stage.setScene(mainScene));
        // When click on quit button, enter the gameover scene
        quitGame.setOnAction(e -> stage.setScene(gameOverScene));
        restartGame.setOnAction(e -> stage.setScene(startMenuScene));

        // display the stage
        stage.show();
        pane.requestFocus();

        // Key Presses:
        // create a hash map(key value pairs stored in a hash table) to store the key
        // presses
        Map<KeyCode, Boolean> key_press = new HashMap<>();
        // add key pressed handler
        mainScene.setOnKeyPressed(event -> {
            key_press.put(event.getCode(), Boolean.TRUE);
        });
        // add key release handler
        mainScene.setOnKeyReleased(event -> {
            key_press.put(event.getCode(), Boolean.FALSE);
        });

        // Animation controls:
        // use an animation timer to update the screen
        new AnimationTimer() {
            // check if j key was pressed so we dont repeatedly go into hyperspace
            // inserted here to prevent multiple jumps
            private boolean jPress = false;
            
            @Override
            public void handle(long now) {
                // if the left key is pressed
                if (key_press.getOrDefault(KeyCode.LEFT, false)) {
                    // rotate the user_ship left
                    ship.turnLeft();
                }
                // if the right key is pressed
                if (key_press.getOrDefault(KeyCode.RIGHT, false)) {
                    // rotate the user_ship right
                    ship.turnRight();
                }

                // if the up key is pressed
                if (key_press.getOrDefault(KeyCode.UP, false)) {
                    // accelerate the user_ship
                    ship.accelerate(0.002);
                }

                // if the J key is pressed for jump and has not already jumped
                if (key_press.getOrDefault(KeyCode.J, false) && jPress==false) {
                    //jump to a new location and if successful set flag to true
                    ship.hyperspaceJump(pane);
                    ship.hyperspaceJump(pane);
                    jPress = true;                   
                }
                // if the J key is released
                if (!key_press.getOrDefault(KeyCode.J, false)) {
                    jPress = false; // reset the flag
                }

                // if the spacebar is pressed, and only 3 bullets on screen
                if (key_press.getOrDefault(KeyCode.SPACE, false) && bullets.size() < 3) {
                    // the bullet appear in the screen
                    // at the same coordinates as current coordinates of the ship
                    // with same rotation angle
                    Bullet bullet = new Bullet((int) ship.getChar().getTranslateX(),
                            (int) ship.getChar().getTranslateY());
                    bullet.getChar().setRotate(ship.getChar().getRotate());

                    // add the new bullet to the list of bullets
                    bullets.add(bullet);

                    // acclerate the speed of the bullet:
                    bullet.accelerate(0.05);

                    // set the movement for the bullet is 3x faster than other character (the ship)
                    bullet.setMovement(bullet.getMovement().multiply(30));

                    pane.getChildren().add(bullet.getChar());
                }

                // update the ship's movement
                ship.move();
                //alien_ship.move();

                // Move the asteriods
                asteroids.forEach(asteroid -> asteroid.move());

                // Move the bullets
                bullets.forEach(bullet -> bullet.move());

                //remove asteroids that are hit by bullets

                // When the collision between an asteroid ...
                asteroids.forEach(asteroid -> {
                    // ... and the ship happens
                    if (asteroid.collision(ship)) {

                        // Remove the collided asteroid from the pane and asteroids list when collision
                        // happens
                        pane.getChildren().remove(asteroid.getChar());
                        asteroids.remove(asteroid);

                        // Then add two new smaller asteroids on the scene and in the asteroids list
                        // If the size=3 asteroid is collided
                        if (asteroid.getInitialSize() == 3) {
                            for (int i = 0; i < 2; i++) {
                                Asteroid newAsteroid = new Asteroid(asteroid.getChar().getTranslateX(),
                                        asteroid.getChar().getTranslateY(), 2);
                                asteroids.add(newAsteroid);
                                pane.getChildren().add(newAsteroid.getChar());
                                newAsteroid.move();
                            }
                        }

                        // If the size=2 asteroid is collided:
                        if (asteroid.getInitialSize() == 2) {
                            for (int i = 0; i < 2; i++) {
                                Asteroid newAsteroid = new Asteroid(asteroid.getChar().getTranslateX(),
                                        asteroid.getChar().getTranslateY(), 1);
                                asteroids.add(newAsteroid);
                                pane.getChildren().add(newAsteroid.getChar());
                                newAsteroid.move();
                            }
                        }
                    }

                    // ... and a bullet happens
                    bullets.forEach(bullet -> {
                        if (asteroid.collision(bullet)) {
                            bullet.setAlive(false);
                            asteroid.setAlive(false);
                        }

                        // adding point
                        if (!bullet.getAlive()) {
                            pointText.setText("Points: " + points.addAndGet(1000));
                        }
                    });
                });

                // turn the ArrayList of asteroids to a list to apply filter & collect method to
                // create a list of collided bullets
                bullets.stream()
                        .filter(bullet -> !bullet.getAlive())
                        .forEach(bullet -> pane.getChildren().remove(bullet.getChar()));

                bullets.removeAll(bullets.stream()
                        .filter(bullet -> !bullet.getAlive())
                        .collect(Collectors.toList()));

                asteroids.stream()
                        .filter(asteroid -> !asteroid.getAlive())
                        .forEach(asteroid -> pane.getChildren().remove(asteroid.getChar()));

                asteroids.removeAll(asteroids.stream()
                        .filter(asteroid -> !asteroid.getAlive())
                        .collect(Collectors.toList()));
            }
        }.start();
    }

    // run the application
    public static void main(String[] args) {
        launch(args);
    }
}