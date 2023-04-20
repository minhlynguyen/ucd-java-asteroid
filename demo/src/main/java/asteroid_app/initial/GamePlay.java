package asteroid_app.initial;

import javafx.animation.AnimationTimer;

public class GamePlay extends AnimationTimer{
    private long framesPerSecond = 50L; 
    private long interval = 1000000000L/framesPerSecond;
    private long last = 0;
    
    @Override
    public void handle(long now){
        if (now - last > interval){
            last = now; 
        }
        System.out.println("Start animation");
    }
}
