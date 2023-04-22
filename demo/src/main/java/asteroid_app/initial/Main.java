package asteroid_app.initial;//this is the package name
// package demo.src.main.java.asteroid_app.initial;

// Application is the base class for all JavaFX applications
//need javafx to display the window
import javafx.application.Application;
// Stage is the top level JavaFX container
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
// Scene is the container for all content
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.util.concurrent.atomic.AtomicInteger;


//window extends the application class from javafx
public class Main extends Application {

    public static Stage stage;

    // define the size of the screen can be accessed by all classes
    public static int WIDTH = 1400;
    public static int HEIGHT = 800;
    // the window class overrides the start mehtod from the application class
    // takes a single parameter of type stage
    // inside the start method is where the User interface is created
    public static int pointX = WIDTH / 2 - 7;
    public static int pointY = 20;

    // initial level = 1
    public static int initLevel = 0;

    public static AtomicInteger points = new AtomicInteger();;

    @Override
    public void start(Stage stage) {

        
        Scene startMenuScene = StartMenu.startMenu(WIDTH, HEIGHT);
        Scene mainScene = GameMenu.newGameMenu(initLevel);
 
        stage.setScene(mainScene);

        // When click on quit button, enter the gameover scene
        Button quitGameButton = (Button) mainScene.getRoot().lookup("#quitGame");
        quitGameButton.setOnAction(null);

        Button restartGameButton = (Button) mainScene.getRoot().lookup("#restartGame");
        restartGameButton.setOnAction(e -> stage.setScene(startMenuScene));

        // set the title of the window
        stage.setTitle("Group 11-Asteroids Game");
        // display the stage
        stage.show();
    }

    // run the application
    public static void main(String[] args) {
        launch(args);
    }
}