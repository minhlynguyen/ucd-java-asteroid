package asteroid_app.custom;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class AlienShip {
    private final double radius = 30;
    private Polygon shape;
    private Point2D position;
    private Point2D velocity;
    private double speed;
    private boolean active;
    private long lastShotTime;

    public AlienShip(double startX, double startY) {
        position = new Point2D(startX, startY);
        velocity = new Point2D(0, 0);
        speed = 5;
        active = true;

        // Define the shape of the alien ship
        shape = new Polygon();
        shape.getPoints().addAll(
                0.0, -radius * 2,
                radius, 0.0,
                0.0, radius,
                -radius, 0.0
        );
        shape.setFill(null);
        shape.setStrokeWidth(2);
    }

    public void moveTowardsTarget(Point2D targetPosition) {
        Point2D direction = targetPosition.subtract(position).normalize();
        velocity = direction.multiply(speed);
    }

    public void updatePosition() {
        position = position.add(velocity);
    }

    public Polygon getShape() {
        return shape;
    }

    public Point2D getPosition() {
        return position;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

