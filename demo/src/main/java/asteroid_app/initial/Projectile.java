package asteroid_app.initial;
import javafx.scene.shape.Polygon;

public class Projectile extends Character{
    
    public Projectile(int x, int y, double direction){
        this.shape = new Polygon(2,-2,2,2,-2,2,-2,-2);
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        this.shape.setRotate(direction);
    }
}
