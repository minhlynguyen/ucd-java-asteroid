package asteroid_app.initial;//this is the package name
// package demo.src.main.java.asteroid_app.initial;

// Application is the base class for all JavaFX applications
//need javafx to display the window
import javafx.application.Application;
// Stage is the top level JavaFX container
import javafx.stage.Stage;
// Scene is the container for all content
import javafx.scene.Scene;
import javafx.scene.control.Button;


//window extends the application class from javafx
public class Main extends Application {

    //public static Stage stage;

    // define the size of the screen can be accessed by all classes
    public static int WIDTH = 1400;
    public static int HEIGHT = 800;
    // the window class overrides the start mehtod from the application class
    // takes a single parameter of type stage
    // inside the start method is where the User interface is created
    public static int pointX = WIDTH / 2 - 7;
    public static int pointY = 20;

    // initial level = 1
    public static int initLevel = 1;

    @Override
    public void start(Stage stage) {

        // start Menu
        Scene startMenuScene = StartMenu.startMenu(WIDTH, HEIGHT);
        // game menu
        Scene newGameScene = new Scene(GameMenu.borderPane);
        // game over menu
        Scene gameOverScene = GameOverMenu.gameOverMenu(WIDTH, HEIGHT);
        // default the start menu 
        stage.setScene(startMenuScene);

        // get the play game button
        Button playGameButton = (Button) startMenuScene.getRoot().lookup("#playGame");
        // click the play game button then change to game menu
        playGameButton.setOnAction(e -> {
            GameMenu.initialize(1);
            GameMenu.start();
            stage.setScene(newGameScene);
        });

        // get the quit game button
        Button quitGameButton = (Button) newGameScene.getRoot().lookup("#quitGame");
        quitGameButton.setOnAction(e -> {            
            GameMenu.stop();
            stage.setScene(gameOverScene);
        });
        
        
        
        // Button restartGameButton = (Button) newGameScene.getRoot().lookup("#restartGame");
        // restartGameButton.setOnAction(e -> stage.setScene(startMenuScene));

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
