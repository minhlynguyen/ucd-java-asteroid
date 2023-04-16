package asteroid_app.initial;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AlienShipCreation {
    private int screenWidth;
    private int screenHeight;
    private AlienShip alienShip;

    public AlienShipCreation(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        initAlienShip();
    }

    private void initAlienShip() {
        alienShip = new AlienShip(0, 0);
        alienShip.setChar(Alien.createAlien());
    }

    public AlienShip getAlienShip() {
        return alienShip;
    }

    public void drawAlienShip(GraphicsContext graphics) {
        if (alienShip.isActive()) {
            graphics.setFill(Color.BLACK);
            graphics.fillPolygon(alienShip.getChar().getPoints().stream().toArray(Double[]::new), alienShip.getChar().getPoints().stream().toArray(Double[]::new), alienShip.getChar().getPoints().size());
        }
    }
}
