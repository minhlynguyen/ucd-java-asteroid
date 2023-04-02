package asteroid_app.initial;
import javafx.scene.shape.Polygon;

public class Bullet extends Character {
    public Bullet(int x, int y){
        super(new Polygon(1,-1,1,1,-1,1,-1,-1), x, y);
    }
    
}

