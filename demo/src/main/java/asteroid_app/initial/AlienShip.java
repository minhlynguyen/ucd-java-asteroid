package asteroid_app.initial;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienShip extends Character {

    private long lastDirectionChangeTime;
    private long lastShotTime;
    private static final long DIRECTION_CHANGE_INTERVAL = 4000;
    private static final long SHOOTING_INTERVAL = 2000;
    public static  int APPEARANCE_INTERVAL = 30000;
    private long lastActiveTime;

    private double movementOffset;
    private double angle;
    private final Random rnd = new Random();
    private List<Bullet> bullets=new ArrayList<>();

    public boolean shoot=false;

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
    public void move(Pane pane,User_ship ship,PlayerLives lives) {
//        double x = this.getChar().getTranslateX() + Math.cos(movementOffset) * 1.5;
//        double y = this.getChar().getTranslateY() + Math.sin(movementOffset) * 1.5;
//        this.movementOffset = rnd.nextDouble() * Math.PI * 2;
        super.move();
//        super.getChar().setRotate(super.getChar().getRotate() + 1);
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
            shoot(pane,ship);
            lastShotTime = System.currentTimeMillis();
        }

        bullets.forEach(bullet -> {//user ship is hit by alien ship
            if (ship.collision(bullet) && bullet.getAlive()) {
                bullet.setAlive(false);
                pane.getChildren().remove(bullet.getChar());
                lives.loseLife();
            }

            double x1 = bullet.getChar().getTranslateX();
            double y1 = bullet.getChar().getTranslateY();
            double travelDistance = Math.sqrt((x1-bullet.getOriginalX())*(x1-bullet.getOriginalX())+(y1-bullet.getOriginalY())*(y1-bullet.getOriginalY()));
            if (travelDistance <= 500){
                double dx = ship.getChar().getTranslateX() - bullet.getChar().getTranslateX();
                double dy = ship.getChar().getTranslateY() - bullet.getChar().getTranslateY();
                double magnitude = Math.sqrt(dx * dx + dy * dy);
                if (magnitude > 0) {
                    bullet.getChar().setTranslateX(bullet.getChar().getTranslateX() + dx / magnitude * 3);
                    bullet.getChar().setTranslateY(bullet.getChar().getTranslateX() + dy / magnitude * 3);
                }
            }else{
                bullet.setAlive(false);
                pane.getChildren().remove(bullet.getChar());
            }

        });
    }

    public void shoot(Pane pane,User_ship ship) {
        if(!this.getAlive()){
            bullets.forEach(bullet -> {
                pane.getChildren().remove(bullet.getChar());
            });
            return;
        }
        Bullet bullet = new Bullet(this.getChar().getTranslateX(), this.getChar().getTranslateY());
        bullet.getChar().setRotate(this.getChar().getRotate());

        // acclerate the speed of the bullet:
        bullet.accelerate(0.55);
        bullet.setMovement(bullet.getMovement().multiply(50));


//        this.getChar().setTranslateX(this.getChar().getTranslateX() + this.getMovement().getX());
//        this.getChar().setTranslateY(this.getChar().getTranslateY() + this.getMovement().getY());
        bullets.add(bullet);

        pane.getChildren().add(bullet.getChar());

    }


    // alien ship appearance interval
    public static int getAppearanceInterval() {
        return APPEARANCE_INTERVAL;
    }

}