package asteroid_app.initial;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Inherits from Character class.

public class AlienShip extends Character {

    // Time of last direction change
    private long lastDirectionChangeTime;
    // Time of last bullet shot
    public long lastShotTime;
    // Time interval for direction change
    private static final long DIRECTION_CHANGE_INTERVAL = 4000;
    // Time interval for bullet shooting
    public static final long SHOOTING_INTERVAL = 2000;
    // Time interval for appearance
    public static  int APPEARANCE_INTERVAL = 20000;
    // Number of steps taken in a single movement iteration
    private static final int NUM_STEPS = 1;
    // Angle of each small step taken during movement
    private static final double STEP_ANGLE = Math.PI / 60;
    // Time of last activity
    private long lastActiveTime;
    // Movement offset and angle
    private double movementOffset;
    private double angle;
    private final Random rnd = new Random();
    // List of bullets
    private List<Bullet> bullets=new ArrayList<>();
    // Constructor for AlienShip class.
    public AlienShip(double x, double y) {
        super(create(), x, y);
        // Initialize time and status
        this.lastDirectionChangeTime = System.currentTimeMillis();
        this.lastActiveTime=System.currentTimeMillis();
        this.lastShotTime = System.currentTimeMillis();
    }

    public static Polygon create() {
        // coordinates of the points of polygon
        double points[] = {
                0.0, 0.0,
                10.0, 0.0,
                20.0, 10.0,
                40.0, 20.0,
                20.0, 30.0,
                -10.0, 30.0,
                -30.0, 20.0,
                -10.0, 10.0
        };
        // create a polygon
        Polygon polygon = new Polygon(points);
        polygon.setFill(Color.BLACK);
        return polygon;
    }

    // Changes the direction of movement.
    private void changeDirection() {
        this.movementOffset = rnd.nextDouble() * Math.PI * 2;
    }


    //    @Override
    public void move(Pane pane,User_ship ship, List<Asteroid> asteroids) {
        // Move and accelerate
        super.move();
        super.accelerate(0.00001);

        // Create an array to store the step angles
        double[] steps = new double[NUM_STEPS];
        // Generate a random step angle and store it in the array for each step
        for (int i = 0; i < NUM_STEPS; i++) {
            steps[i] = (rnd.nextDouble() - 0.5) * STEP_ANGLE;
        }

        // Move the alien ship in small steps by adding the change in x and y coordinates
        for (double step : steps) {
            angle += step;
            double changeX = Math.cos(angle);
            double changeY = Math.sin(angle);
            this.getChar().setTranslateX(this.getChar().getTranslateX() + changeX);
            this.getChar().setTranslateY(this.getChar().getTranslateY() + changeY);
        }
        // Change direction if necessary
        if (System.currentTimeMillis() - lastDirectionChangeTime > DIRECTION_CHANGE_INTERVAL) {
            changeDirection();
            lastDirectionChangeTime = System.currentTimeMillis();
        }

        // Reappear if it is past the appearance interval and it is not alive
        if (System.currentTimeMillis() - lastActiveTime > (APPEARANCE_INTERVAL)) {
            APPEARANCE_INTERVAL=15000;
            if(!this.getAlive()) {
                // Set the position of the alien ship to a random location within the pane
                double x = Math.random() * pane.getWidth();
                double y = Math.random() * pane.getHeight();
                this.getChar().setTranslateX(x);
                this.getChar().setTranslateY(y);
                pane.getChildren().add(this.getChar());
                this.setAlive(true);
            }
            lastActiveTime = System.currentTimeMillis();
        }

        // shooting
        if (System.currentTimeMillis() - lastShotTime > SHOOTING_INTERVAL) {
            //shoot(pane);
            if(this.getAlive()){
                // Create a new bullet and add it to the bullet list and the pane
                Bullet bullet = this.fireBullet();
                bullets.add(bullet);
                pane.getChildren().add(bullet.getChar());
                this.lastShotTime = System.currentTimeMillis();
            }
        }
        // Move and update the state of each bullet in the bullet list
        bullets.forEach(bullet -> {
            if (this.getAlive()) {
                bullet.getChar().setRotate(Math.random() * 90);
                bullet.setMovement(bullet.getMovement().normalize().multiply(10));
                bullet.setMovement(bullet.getMovement().normalize().multiply(5));
                bullet.accelerate(0.02);
                bullet.move();
                // Remove the bullet from the pane and the bullet list if it travels too far away
                double x1 = bullet.getChar().getTranslateX();
                double y1 = bullet.getChar().getTranslateY();
                double travelDistance = Math.sqrt((x1-bullet.getOriginalX())*(x1-bullet.getOriginalX())+(y1-bullet.getOriginalY())*(y1-bullet.getOriginalY()));
                if (travelDistance > Main.WIDTH/2){
                    bullet.setAlive(false);
                    pane.getChildren().remove(bullet.getChar());
                }
            }else{
                bullets.remove(bullet);
                pane.getChildren().remove(bullet.getChar());
            }
        });

        // Check if the bullet hits the user's ship and decrease the player's lives if it does
        bullets.forEach(bullet -> {
            if (ship.collision(bullet) && bullet.getAlive()) {
                bullet.setAlive(false);
                bullets.remove(bullet);
                pane.getChildren().remove(bullet.getChar());
                Main.playerLives.loseLife();
            }

            // Check if the bullet hits any asteroid and split it into smaller asteroids if it does
            asteroids.forEach(asteroid -> {
                if(asteroid.collision(bullet)){
                    bullet.setAlive(false);
                    bullets.remove(bullet);
                    pane.getChildren().remove(bullet.getChar());
                    asteroid.setAlive(false);
                    Asteroid.asteroidSplit(asteroid, asteroids, pane);
                }
            });
        });
        
    }

    // alien ship appearance interval
    public static int getAppearanceInterval() {
        return APPEARANCE_INTERVAL;
    }

}