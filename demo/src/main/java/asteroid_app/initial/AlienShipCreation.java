package asteroid_app.initial;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AlienShipCreation {
    private int screenWidth;
    private int screenHeight;
    private Random random;
    private AlienShip alienShip;

    public AlienShipCreation(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.random = new Random();
        initAlienShip();
    }

    private void initAlienShip() {
        alienShip = new AlienShip();
        alienShip.setPosition(new Point(0, 0));
        alienShip.setVelocity(new Vector(0, 0));
        alienShip.setRotation(0);
        alienShip.setRadius(10);
        List<Point> points = createAlienShipShape();
        alienShip.setPoints(points);
        alienShip.setActive(false);
        alienShip.setSpawnTimer(Utils.randomInt(1200, 2400));
    }

    private List<Point> createAlienShipShape() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(8, 0));
        points.add(new Point(-8, 6));
        points.add(new Point(-4, 0));
        points.add(new Point(-8, -6));
        points.add(new Point(8, 0));
        return points;
    }

    public AlienShip getAlienShip() {
        return alienShip;
    }

    public void drawAlienShip(Graphics graphics) {
        if (alienShip.isActive()) {
            drawVectorShape(graphics, alienShip);
        }
    }

    private void drawVectorShape(Graphics graphics, AlienShip ship) {
        // Drawing code for the alien ship goes here
    }
}
