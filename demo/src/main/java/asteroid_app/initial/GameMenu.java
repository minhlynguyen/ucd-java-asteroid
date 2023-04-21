package asteroid_app.initial;

// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Distant;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.*;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;

//Stuff for keypresses
import java.util.HashMap;
import javafx.scene.input.KeyCode;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;

public class GameMenu {

    public static Scene newGameMenu(int level) {

        // create a pane and set size
        Pane pane = new Pane();
        System.out.println("New Pane created");
        pane.setPrefSize(Main.WIDTH, Main.HEIGHT);

        Scene mainScene = new Scene(pane);
        

        // Create a hbox to display points and level
        HBox hBox = new HBox(400);
        hBox.setAlignment(Pos.CENTER);

        // text to display points
        Text pointText = new Text(Main.pointX, Main.pointY, "Points:" + Main.points.get());

        // text to display levels
        // Text levelText = new Text(Main.pointX, Main.pointY, "Level:" + level);

        hBox.getChildren().addAll(
            //levelText, 
            pointText);
        pane.getChildren().add(hBox);
        
        // QuitGame button
        Button quitGame = new Button("QUIT");
        quitGame.setId("quitGame");
        Button restartGame = new Button("RESTART");
        restartGame.setId("restartGame");

        // Control box
        HBox controlBox = new HBox(10, quitGame, restartGame);
        controlBox.setAlignment(Pos.CENTER);
        pane.getChildren().add(controlBox);
        controlBox.setTranslateX(Main.WIDTH*0.9);
        controlBox.setTranslateY(Main.HEIGHT*0.01);

        pane.requestFocus();
        
        AnimationTimer loop = new AnimationTimer(){
            
            @Override
            public void start() {
                GameAnimation.initialize(level, pane, mainScene);
                super.start();
            };

            @Override
            public void handle(long now) {
    
                GameAnimation.play(level, pane, mainScene);
                pointText.setText("Points: " + Main.points.get());
    
            }
        };

        // Animation controls:
        // use an animation timer to update the screen
        
        // if (GameAnimation.getAsteroidNumber() > 0){
        //     GameAnimation.initialize(level+1, pane, mainScene);
        loop.start();
        //}else{
          //  loop.stop();
        //}

        return mainScene;
    }
}