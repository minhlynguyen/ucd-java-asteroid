package asteroid_app.initial;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

public class Bullet extends Character {

    private double x0;
    private double y0;
    private double travelDistance;
    private double deltaX;
    private double deltaY;

    public Bullet(double x, double y, Pane pane) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y, pane);
        this.x0 = x;
        this.y0 = y;
    }

    public void move(Pane pane) {
        deltaX = this.getMovement().getX();
        deltaY = this.getMovement().getY();
        travelDistance = Math.sqrt(deltaX*deltaX+deltaY*deltaY);

        if(travelDistance <= Main.WIDTH){
            this.getChar().setTranslateX(this.getChar().getTranslateX() + deltaX);
            this.getChar().setTranslateY(this.getChar().getTranslateY() + deltaY);
        }else{
            pane.getChildren().remove(this.getChar());
        }
    }

    public double getOriginalX(){
        return this.x0;
    }
    
    public double getOriginalY(){
        return this.y0;
    }
}

