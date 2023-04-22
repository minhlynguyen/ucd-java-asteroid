package asteroid_app.initial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class GameController {
    private int level = 0;
    // Asteroid
    // At the beginning, use a list to create several asteroid
    private List<Asteroid> asteroids = new ArrayList<>();
    private User_ship ship;
    private List<Bullet> bullets = new ArrayList<>();
    private Map<KeyCode, Boolean> key_press = new HashMap<>();
    private Boolean jPress;
    private Boolean spacePress;

    public GameController(int level, Pane pane, Scene mainScene){
        jPress = false;
        spacePress = false;
        ship = new User_ship(Main.WIDTH / 2, Main.HEIGHT / 2);
        pane.getChildren().add(ship.getChar());

        for (int i = 0; i <= level; i++) {
            double x = new Random().nextDouble() * 1000;
            double y = new Random().nextDouble() * 1000;
            System.out.println("A new asteroid at "+x+","+y);
            asteroids.add(new Asteroid(x, y, Size.LARGE));
        }
        asteroids.forEach(asteroid -> pane.getChildren().add(asteroid.getChar()));
        
        key_press = new HashMap<>();
        mainScene.setOnKeyPressed(event -> {
            key_press.put(event.getCode(), Boolean.TRUE);
        });
        // add key release handler
        mainScene.setOnKeyReleased(event -> {
            key_press.put(event.getCode(), Boolean.FALSE);
        });
        // if the left key is pressed        
    }


    public void play(Pane pane, Scene mainScene){
        ship.move();        
        asteroids.forEach(asteroid -> asteroid.move());
        // check if j key was pressed so we dont repeatedly go into hyperspace
        // inserted here to prevent multiple jumps
        // private boolean jPress = false;
        // private boolean spacePress=false;    
        // if the up key is pressed
        if (key_press.getOrDefault(KeyCode.UP, false)) {
            // accelerate the user_ship
            ship.accelerate(0.002);
        }
        if (key_press.getOrDefault(KeyCode.LEFT, false)) {
            // rotate the user_ship left
            ship.turnLeft();
        }
        // if the right key is pressed
        if (key_press.getOrDefault(KeyCode.RIGHT, false)) {
            // rotate the user_ship right
            ship.turnRight();
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
        if (key_press.getOrDefault(KeyCode.SPACE, false) && spacePress==false) {
            // the bullet appear in the screen
            // at the same coordinates as current coordinates of the ship
            // with same rotation angle
            Bullet bullet = ship.fireBullet();

            // add the new bullet to the list of bullets
            bullets.add(bullet);

            // acclerate the speed of the bullet:
            bullet.accelerate(0.002);

            // set the movement for the bullet is 3x faster than other character (the ship)
            bullet.setMovement(bullet.getMovement().normalize().multiply(10));

            pane.getChildren().add(bullet.getChar());
            spacePress = true;
        }

        // if the spacebar is released
        if (!key_press.getOrDefault(KeyCode.SPACE, false)) {
            spacePress =false ; // reset the flag
        }
        
        asteroids.forEach(asteroid -> { 
            // ... and the ship happens
            if (asteroid.collision(ship)) {
                // then create new asteroids and remove the collided one
                Asteroid.asteroidSplit(asteroid, asteroids, pane);
                // if number of asteroids < 0, level ++ 
                // if (asteroids.size() == 0) {
                //     levelUp(level);
                // }
            }

            // ... and when a bullet happens
            bullets.forEach(bullet -> {
                if (asteroid.collision(bullet)) {
                    bullet.setAlive(false);
                    asteroid.setAlive(false);
                    Asteroid.asteroidSplit(asteroid, asteroids, pane);
                    // if number of asteroids < 0, level ++ 
                    // if (asteroids.size() == 0) {
                    //     levelUp(level);
                    // }
                }

                // adding point
                if (!bullet.getAlive()) {
                    Main.points.addAndGet(1000);
                }
            });            
        });

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
        
        bullets.forEach(bullet -> {
            double x1 = bullet.getChar().getTranslateX();
            double y1 = bullet.getChar().getTranslateY();
            double travelDistance = Math.sqrt((x1-bullet.getOriginalX())*(x1-bullet.getOriginalX())+(y1-bullet.getOriginalY())*(y1-bullet.getOriginalY()));
            if (travelDistance <= Main.WIDTH){
                bullet.move();
            }else{
                pane.getChildren().remove(bullet.getChar());
            }
        });
    }

    // public static int getAsteroidNumber(){
    //     return asteroids.size();
    // }

    // public static void levelUp(int currentLevel) {
    //     currentLevel++;
    //     resetGame(currentLevel);
    // }

    // public static void resetGame(int level) {
    //     Main.stage.setScene(GameMenu.newGameMenu(level));
    // }
}
