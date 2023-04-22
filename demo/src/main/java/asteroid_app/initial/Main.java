package asteroid_app.initial;//this is the package name

// Application is the base class for all JavaFX applications
import javafx.application.Application;
import javafx.stage.Stage;
// Scene is the container for all content
import javafx.scene.Scene;
import java.util.concurrent.atomic.AtomicInteger;


// Main extends the application class from javafx
public class Main extends Application {

    // Stage is the top level JavaFX container
    public static Stage stage;

    // define the size of the screen can be accessed by all classes
    public static int WIDTH = 1400;
    public static int HEIGHT = 800;

    // takes a single parameter of type stage
    // inside the start method is where the User interface is created
    public static int pointX = WIDTH / 2 - 7;
    public static int pointY = 20;

    // initial level = 1
    public static int initLevel = 0;

    public static AtomicInteger points = new AtomicInteger();;

    // Overrides the start mehtod from the application class
    @Override
    public void start(Stage stage) {
        
        Scene mainScene = GameMenu.newGameMenu();
        stage.setScene(mainScene);
        stage.setTitle("Group 11-Asteroids Game");
        stage.show();
    }

    // run the application
    public static void main(String[] args) {
        launch(args);
    }
}