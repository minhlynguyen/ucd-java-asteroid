package asteroid_app.initial;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienShip extends Character {

    private long lastDirectionChangeTime;
    public long lastShotTime;
    private static final long DIRECTION_CHANGE_INTERVAL = 4000;
    public static final long SHOOTING_INTERVAL = 2000;
    public static  int APPEARANCE_INTERVAL = 30000;
    private long lastActiveTime;

    private double movementOffset;
    private double angle;
    private final Random rnd = new Random();
    private List<Bullet> bullets=new ArrayList<>();

    public AlienShip(double x, double y) {
        super(create(), x, y);
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
        polygon.setFill(Color.GRAY);
        return polygon;
    }


    private void changeDirection() {
        this.movementOffset = rnd.nextDouble() * Math.PI * 2;
    }


    //    @Override
    public void move(Pane pane,User_ship ship) {
        super.move();
        super.accelerate(0.5);
        double randomOffset = (rnd.nextDouble() - 0.5) * 0.2;
        movementOffset += 0.05 + randomOffset;

        // change direction
        if (System.currentTimeMillis() - lastDirectionChangeTime > DIRECTION_CHANGE_INTERVAL) {
            changeDirection();
            lastDirectionChangeTime = System.currentTimeMillis();
        }

        // After being hit, reappear in 20s
        if (System.currentTimeMillis() - lastActiveTime > (APPEARANCE_INTERVAL)) {
            APPEARANCE_INTERVAL=20000;
            if(!this.getAlive()) {
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
                Bullet bullet = this.fireBullet();
                bullets.add(bullet);
                pane.getChildren().add(bullet.getChar());
                this.lastShotTime = System.currentTimeMillis();
            }
        }

        bullets.forEach(bullet -> {
            bullet.getChar().setRotate(Math.random()*360);
            bullet.setMovement(bullet.getMovement().normalize().multiply(10));
            bullet.setMovement(bullet.getMovement().normalize().multiply(5));
            bullet.accelerate(0.02);
            bullet.move();

            double x1 = bullet.getChar().getTranslateX();
            double y1 = bullet.getChar().getTranslateY();
            double travelDistance = Math.sqrt((x1-bullet.getOriginalX())*(x1-bullet.getOriginalX())+(y1-bullet.getOriginalY())*(y1-bullet.getOriginalY()));
            if (travelDistance > 500){
                bullet.setAlive(false);
                pane.getChildren().remove(bullet.getChar());
            }

            //user ship is hit by alien ship
            if (ship.collision(bullet) && bullet.getAlive()) {
                bullet.setAlive(false);
                pane.getChildren().remove(bullet.getChar());
                Main.playerLives.loseLife();
            }
        });
    }

    // alien ship appearance interval
    public static int getAppearanceInterval() {
        return APPEARANCE_INTERVAL;
    }

}