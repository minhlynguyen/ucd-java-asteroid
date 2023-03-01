//Pane class allows us to add a pane to the window
//This program will create a window pane with a title

// Application is the base class for all JavaFX applications
import javafx.application.Application;
// Stage is the top level JavaFX container
import javafx.stage.Stage;
// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.layout.Pane;
// Label for the title
import javafx.scene.control.Label;

public class Window extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        // create a pane
        Pane pane = new Pane();
        // set pane size
        pane.setPrefSize(800, 600);
        // create label
        Label label = new Label("Start of Asteroids");
        // add label to pane
        pane.getChildren().add(label);

        // create a scene
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        // Display the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


