package asteroid_app.initial;

import java.io.Serializable;

public class HighScore implements Serializable{
    String name;
    int score;

    public HighScore(String name, int score){
        this.name = name;
        this.score = score;
    }
    
}
