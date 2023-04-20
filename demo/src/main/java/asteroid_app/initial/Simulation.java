package asteroid_app.initial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Simulation {
    
    private User_ship ship;
    private List<Asteroid> asteroids;
    private List<Bullet> bullets;
    private Scene mainScene;
    private boolean jPress;
    private boolean spacePress;
    private Text pointText;
    private AtomicInteger points = new AtomicInteger();


    public Simulation(Pane pane, int level){ 
        // Create characters in the pane
        mainScene = new Scene(pane);
        // A ship in the middle of the pane
        ship = new User_ship(Main.WIDTH / 2, Main.HEIGHT / 2, pane); // In the constructor, the input pane will get a children

        // Create the asteroids
        asteroids = new ArrayList<Asteroid>();
        for (int i = 0; i < level; i++) {
            double x = new Random().nextDouble() * 1000;
            double y = new Random().nextDouble() * 1000;
            Asteroid asteroid = new Asteroid(x, y, Size.LARGE, pane);
            asteroids.add(asteroid);
        }
        
        // Create the bullets
        bullets = new ArrayList<Bullet>();

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

        jPress = false;
        spacePress=false;
        
        // check if j key was pressed so we dont repeatedly go into hyperspace
        // inserted here to prevent multiple jumps
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
        
        // if the spacebar is pressed
        if (key_press.getOrDefault(KeyCode.SPACE, false) && spacePress==false) {
            // the bullet appear in the screen
            // at the same coordinates as current coordinates of the ship
            // with same rotation angle
                        
            Bullet bullet = new Bullet(ship.getChar().getTranslateX(),
                    ship.getChar().getTranslateY(), pane);
            bullet.getChar().setRotate(ship.getChar().getRotate());
        
            // add the new bullet to the list of bullets
            bullets.add(bullet);
        
            // acclerate the speed of the bullet:
            bullet.accelerate(0.05);

            // set the movement for the bullet is 3x faster than other character (the ship)
            bullet.setMovement(bullet.getMovement().multiply(30));

            spacePress = true;
        }

        // if the spacebar is released
        if (!key_press.getOrDefault(KeyCode.SPACE, false)) {
            spacePress =false ; // reset the flag
        }
        
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
        
                // ... and a bullet happens
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

    public List<Asteroid> getAsteroids(){
        return asteroids;
    }

    public void move(){
        asteroids.forEach(asteroid -> asteroid.move());
        ship.move();
        bullets.forEach(bullet -> bullet.move());
        System.out.println("Get everyone moving");        
    }

    public static void levelUp(int currentLevel) {
        currentLevel++;
    }
}
