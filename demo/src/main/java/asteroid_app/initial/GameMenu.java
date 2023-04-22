package asteroid_app.initial;

// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.control.Button;
import javafx.scene.effect.Light.Distant;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.*;

import javafx.scene.layout.BorderPane;
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
     
    static GameController game;  

    public static Scene newGameMenu(int level) {
        
        // create a pane and set size
        BorderPane root = new BorderPane();
        Pane pane = new Pane();
        root.setPrefSize(Main.WIDTH, Main.HEIGHT);

        root.setCenter(pane);

        Scene mainScene = new Scene(root);
        

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

        root.setTop(hBox);
        

        class Movement extends AnimationTimer{
            @Override
            public void handle(long now){
                game.play(pane, mainScene);
            }
        }
        
        Movement clock = new Movement();
        game = new GameController(1, pane, mainScene);
        clock.start();
        game.play(pane, mainScene);

        Button restartGame = new Button("RESTART");
        restartGame.setId("restartGame");
        restartGame.setOnAction(e -> {
            pane.getChildren().clear();
            game = new GameController(1, pane, mainScene);
            clock.start();
            pane.requestFocus();
        }
        );
        // QuitGame button
        Button quitGame = new Button("QUIT");
        quitGame.setId("quitGame");
        quitGame.setOnAction(e->{
            clock.stop();
            pane.getChildren().clear();
            pane.requestFocus();
        });

        // Control box
        HBox controlBox = new HBox(10, quitGame, restartGame);
        controlBox.setAlignment(Pos.CENTER);

        root.setBottom(controlBox);
        pane.requestFocus();

        return mainScene;
    }
}