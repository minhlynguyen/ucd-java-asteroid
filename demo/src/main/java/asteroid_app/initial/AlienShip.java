package asteroid_app.initial;

import javafx.scene.shape.Polygon;
import java.util.Random;

public class AlienShip extends Character {

    private double movingAngle;
    private long lastDirectionChangeTime;
    private long lastShotTime;
    private static final long DIRECTION_CHANGE_INTERVAL = 2000; // 2000ms，或者根据需要调整
    private static final long SHOOTING_INTERVAL = 1000; // 1000ms，或者根据需要调整
    public static final int APPEARANCE_INTERVAL = 150; // 500 game ticks, adjust as needed
    
    private double movementOffset;
    private Random rnd = new Random();
    

    public AlienShip(double x, double y) {
        super(new PolygonCreator().createPolygon(3), x, y);
        this.movementOffset = rnd.nextDouble() * Math.PI * 2;
        this.lastDirectionChangeTime = System.currentTimeMillis();
        this.lastShotTime = System.currentTimeMillis();
}


    // 覆盖 move 方法，以便实现外星飞船的运动
    @Override
    public void move() {
        double x = this.getChar().getTranslateX() + Math.cos(movementOffset) * 2;
        double y = this.getChar().getTranslateY() + Math.sin(movementOffset) * 2;

        this.getChar().setTranslateX(x);
        this.getChar().setTranslateY(y);
        
        double randomOffset = (rnd.nextDouble() - 0.5) * 0.1;
        movementOffset += 0.05 + randomOffset;
        // 实现屏幕边缘的循环移动效果
        loopScreen();

        // 随机改变移动方向
        if (System.currentTimeMillis() - lastDirectionChangeTime > DIRECTION_CHANGE_INTERVAL) {
            changeDirection();
            lastDirectionChangeTime = System.currentTimeMillis();
        }

        // 在外星飞船移动时向玩家射击
        if (System.currentTimeMillis() - lastShotTime > SHOOTING_INTERVAL) {
            shootAtUserShip();
            lastShotTime = System.currentTimeMillis();
        }
    }

    private void loopScreen() {
        double x = this.getChar().getTranslateX();
        double y = this.getChar().getTranslateY();
        double maxX = 800; // 屏幕宽度，根据实际情况调整
        double maxY = 600; // 屏幕高度，根据实际情况调整

        if (x < 0) {
            this.getChar().setTranslateX(maxX);
        } else if (x > maxX) {
            this.getChar().setTranslateX(0);
        }

        if (y < 0) {
            this.getChar().setTranslateY(maxY);
        } else if (y > maxY) {
            this.getChar().setTranslateY(0);
        }
    }

    private void changeDirection() {
        this.movementOffset = rnd.nextDouble() * Math.PI * 2;
    }

    public void shootAtUserShip(User_ship userShip) {
    long currentTime = System.currentTimeMillis();
    if (currentTime > lastShotTime + SHOOTING_INTERVAL) {
        Bullet bullet = new Bullet(this.getChar().getTranslateX(), this.getChar().getTranslateY());

        double deltaX = userShip.getChar().getTranslateX() - this.getChar().getTranslateX();
        double deltaY = userShip.getChar().getTranslateY() - this.getChar().getTranslateY();

        double angle = Math.atan2(deltaY, deltaX);

        bullet.getMovement().setX(Math.cos(angle) * BULLET_SPEED);
        bullet.getMovement().setY(Math.sin(angle) * BULLET_SPEED);

        // Add the bullet to the game scene and the list of bullets (implementation depends on your game structure)
        // Example: pane.getChildren().add(bullet.getChar());
        //          bulletsList.add(bullet);

        lastShotTime = currentTime;
    }
 }
}
