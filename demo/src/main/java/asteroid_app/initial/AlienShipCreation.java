package asteroid_app.initial;


import asteroid_app.custom.AlienShip;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

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
    }

    public AlienShip getAlienShip() {
        return alienShip;
    }

     public void drawAlienShip(GraphicsContext graphics) {
        if (alienShip.isActive()) {
            graphics.setFill(Color.BLACK);
            Polygon shape = alienShip.getShape();
            Point2D position = alienShip.getPosition();

            // Save the current graphics transform
            graphics.save();

            // Set the rotation and translation for the alien ship
            Rotate rotation = new Rotate(0, position.getX(), position.getY());
            graphics.setTransform(rotation.getMxx(), rotation.getMyx(), rotation.getMxy(), rotation.getMyy(), rotation.getTx(), rotation.getTy());

            // Draw the alien ship
            graphics.fillPolygon(
                    shape.getPoints().stream().mapToDouble(Double::doubleValue).toArray(),
                    shape.getPoints().stream().mapToDouble(Double::doubleValue).toArray(),
                    shape.getPoints().size() / 2
            );

            // Restore the original graphics transform
            graphics.restore();
        }
    }
}
