package asteroid_app.initial;

import javafx.geometry.Point2D;
import javafx.scene.Node;

public class KeepObjectsOnScreen {
    private int screenWidth;
    private int screenHeight;
    private boolean isOffScreen = false;

   public KeepObjectsOnScreen(int screenWidth, int screenHeight) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
}

public void checkBoundaries(Node node) {
    double x = node.getTranslateX();
    double y = node.getTranslateY();
    double radius = Math.max(node.getBoundsInLocal().getWidth(), node.getBoundsInLocal().getHeight()) / 2;

    // Check if the node is off the left or right edge of the screen
    if (x < -radius) {
        x = screenWidth + radius;
        isOffScreen = true;
    } else if (x > screenWidth + radius) {
        x = -radius;
        isOffScreen = true;
    }

    // Check if the node is off the top or bottom edge of the screen
    if (y < -radius) {
        y = screenHeight + radius;
        isOffScreen = true;
    } else if (y > screenHeight + radius) {
        y = -radius;
        isOffScreen = true;
    }

    if (isOffScreen) {
        node.setTranslateX(x);
        node.setTranslateY(y);
        isOffScreen = false;
    }
  }

}
