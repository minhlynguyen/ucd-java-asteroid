package asteroid_app.initial;

import javafx.scene.shape.Polygon;

public class Bullet extends Character {

    private double x0;
    private double y0;

    public Bullet(double x, double y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
        this.x0 = x;
        this.y0 = y;
    }

    public void move() {
        this.getChar().setTranslateX(this.getChar().getTranslateX() + this.getMovement().getX());
        this.getChar().setTranslateY(this.getChar().getTranslateY() + this.getMovement().getY());
    }

    public double getOriginalX(){
        return this.x0;
    }
    
    public double getOriginalY(){
        return this.y0;
    }
}