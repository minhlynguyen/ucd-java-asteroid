package asteroid_app.initial;

import javafx.scene.Node;

public class KeepObjectsOnScreen {
    private int screenWidth;
    private int screenHeight;
    private boolean isOffScreen = false;

    public KeepObjectsOnScreen(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void checkBoundaries(Ship ship) {
        int x = ship.getPosition().x;
        int y = ship.getPosition().y;
        int radius = ship.getRadius();

        // Check if the ship is off the left or right edge of the screen
        if (x < -radius) {
            x = screenWidth + radius;
            isOffScreen = true;
        } else if (x > screenWidth + radius) {
            x = -radius;
            isOffScreen = true;
        }

        // Check if the ship is off the top or bottom edge of the screen
        if (y < -radius) {
            y = screenHeight + radius;
            isOffScreen = true;
        } else if (y > screenHeight + radius) {
            y = -radius;
            isOffScreen = true;
        }

        if (isOffScreen) {
            ship.setPosition(new Point(x, y));
            isOffScreen = false;
        }
    }
}
