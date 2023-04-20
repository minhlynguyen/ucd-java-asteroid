package asteroid_app.initial;

// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
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

    static BorderPane borderPane = new BorderPane(); //The whole game user interface
    // static Scene newGameScene = new Scene(borderPane);
    static ToolBar toolbar = new ToolBar(); //The toolbar to control the game
    static HBox statusbar = new HBox(); //Display point and level
    static Pane pane = new Pane(); //The game pane where all characters appear

    // private GamePlay clock;
    static Simulation sim = new Simulation(pane,1);;
    static GamePlay clock;

    public static void initialize(int level){ 
        // Setup borderPane
        borderPane.setBottom(toolbar);
        borderPane.setCenter(pane);
        borderPane.setTop(statusbar);
        System.out.println("Set border pane"); 

        // Setup status bar (points and level)
        Text pointText = new Text(Main.pointX, Main.pointY, "Points: 0");
        Text levelText = new Text(Main.pointX, Main.pointY, "Level:" + level);
        statusbar.getChildren().addAll(levelText, pointText);
            
        // calculate the point

        // Setup toolbar
        Button quitGame = new Button("QUIT");
        quitGame.setId("quitGame");
        quitGame.setFocusTraversable(false);

        Button restartGame = new Button("RESTART");
        restartGame.setId("restartGame");
        restartGame.setFocusTraversable(false);

        toolbar.getItems().add(quitGame);
        toolbar.getItems().add(restartGame);

        // Setup Pane
        pane.setPrefSize(Main.WIDTH, Main.HEIGHT);   
        pane.requestFocus();

        clock = new GamePlay();
        clock.start();
    }

    public static void reset(){
        clock.start();
        pane.getChildren().clear();
        sim = new Simulation(pane,1);
        sim.move();
    }

    public static void start(){ 
        // clock.start();
        sim.move();
    }

    public static void stop(){
        clock.stop();
    }
}