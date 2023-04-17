package asteroid_app.initial;

// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

//Stuff for keypresses
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;

public class GameMenu {
    private AlienShipCreation alienShipCreation;

    // constructor
    public GameMenu(int level) {
        // initialize AlienShipCreation object
        alienShipCreation = new AlienShipCreation(Main.WIDTH, Main.HEIGHT);
        // other initialization code...
    }

    public void update() {
        // update player ship, asteroids, etc.
        // update alien ship
        alienShipCreation.getAlienShip().moveTowardsTarget(playerShip.getPosition());
        alienShipCreation.getAlienShip().updatePosition();
        // check for collisions
    }

    public void draw(GraphicsContext graphics) {
        // draw player ship, asteroids, etc.
        // draw alien ship
        alienShipCreation.drawAlienShip(graphics);
    }
    private IncrementScore score;
    private PlayerLives lives;

    public GameMenu() {
        score = new IncrementScore();
        lives = new PlayerLives();
    }

    public IncrementScore getScore() {
        return score;
    }

    public PlayerLives getLives() {
        return lives;
    }


   public Scene newGameMenu(int level) {

        Pane pane = new Pane();
        pane.setPrefSize(Main.WIDTH, Main.HEIGHT);

        Scene mainScene = new Scene(pane);

        HBox hBox = new HBox(400);
        hBox.setAlignment(Pos.CENTER);

        Text pointText = new Text(Main.pointX, Main.pointY, "Points: " + score.getScore());

        Text levelText = new Text(Main.pointX, Main.pointY, "Level: " + level);

        Text livesText = new Text(Main.pointX, Main.pointY, "Lives: " + lives.getLives());

        hBox.getChildren().addAll(levelText, pointText, livesText);
        pane.getChildren().add(hBox);

        
        // calculate the point
        AtomicInteger points = new AtomicInteger();

        // Object creation:
        // create the characters

        // Asteroid
        // At the beginning, use a list to create several asteroid
        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < level; i++) {
            double x = new Random().nextDouble() * 1000;
            double y = new Random().nextDouble() * 1000;
            Asteroid asteroid = new Asteroid(x, y, 3);
            asteroids.add(asteroid);
        }

        // Add these asteroids to Pane
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getChar()));

        // Alien
       AlienShipMovementAndShooting alienShipMovement = new AlienShipMovementAndShooting(2, 0, alienShipCreation.getAlienShip(), getLives());

        // Ship
        // create a user_ship object and initialize location
        User_ship ship = new User_ship(Main.WIDTH / 2, Main.HEIGHT / 2);
        // add the user_ship to the pane
        pane.getChildren().add(ship.getChar());


        // Bullet
        List<Bullet> bullets = new ArrayList<>();

        // QuitGame button
        Button quitGame = new Button("QUIT");
        quitGame.setId("quitGame");
        Button restartGame = new Button("RESTART");
        restartGame.setId("restartGame");

        // Control box
        HBox controlBox = new HBox(10, quitGame, restartGame);
        controlBox.setAlignment(Pos.CENTER);
        pane.getChildren().add(controlBox);
        controlBox.setTranslateX(Main.WIDTH*0.9);
        controlBox.setTranslateY(Main.HEIGHT*0.01);

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
            private boolean spacePress=false;

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
                if (key_press.getOrDefault(KeyCode.J, false) && jPress == false) {
                    // jump to a new location and if successful set flag to true
                    ship.hyperspaceJump(pane);
                    ship.hyperspaceJump(pane);
                    jPress = true;
                }
                // if the J key is released
                if (!key_press.getOrDefault(KeyCode.J, false)) {
                    jPress = false; // reset the flag
                }

                // if the spacebar is pressed, and only 3 bullets on screen
                if (key_press.getOrDefault(KeyCode.SPACE, false)&& bullets.size() < 3 && spacePress==false) {
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
                    spacePress = true;
                }

                // if the spacebar is released
                if (!key_press.getOrDefault(KeyCode.SPACE, false)) {
                    spacePress =false ; // reset the flag
                }

                // update the ship's movement
                ship.move();
                
                alien_ship.move();
                

                // Move the asteriods
                asteroids.forEach(asteroid -> asteroid.move());

                // Move the bullets
                bullets.forEach(bullet -> bullet.move());



                // When the collision between an asteroid ...
                asteroids.forEach(asteroid -> { 
                    // ... and the ship happens
                    if (asteroid.collision(ship)) {
                        // then create new asteroids and remove the collided one
                        Asteroid.asteroidSplit(asteroid, asteroids, pane);
                        // if number of asteroids < 0, level ++ 
                        if (asteroids.size() <= 0) {
                            levelUp(level);
                        }
                    }

                    // ... and when a bullet happens
                    bullets.forEach(bullet -> {
                        if (asteroid.collision(bullet)) {
                            bullet.setAlive(false);
                            asteroid.setAlive(false);
                            Asteroid.asteroidSplit(asteroid, asteroids, pane);
                            // if number of asteroids < 0, level ++ 
                            if (asteroids.size() <= 0) {
                                    levelUp(level);
                                }
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

        return mainScene;
    }


    public static void levelUp(int currentLevel) {

        currentLevel++;
        resetGame(currentLevel);

    }

    public static void resetGame(int level ) {
        Main.stage.setScene(newGameMenu(level));
    }
}
