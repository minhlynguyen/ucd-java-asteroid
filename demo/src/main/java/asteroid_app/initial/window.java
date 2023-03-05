//this is the package name
package asteroid_app.initial;

// Application is the base class for all JavaFX applications
//need javafx to display the window
import javafx.application.Application;
// Stage is the top level JavaFX container
import javafx.stage.Stage;
// Scene is the container for all content
import javafx.scene.Scene;
// Pane is the base class for all layout panes
import javafx.scene.layout.Pane;
//import circle to draw a circle
import javafx.scene.shape.Circle;
// Label for the text inside the window
import javafx.scene.control.Label;

//window extends the application class from javafx
public class window extends Application{

    //the window class overrides the start mehtod from the application class
    //takes a single parameter of type stage
    //inside the start method is where the User interface is created
    @Override
    public void start(Stage stage) throws Exception{
        // create a pane
        Pane pane = new Pane();
        // set pane size
        pane.setPrefSize(600, 400);
        // create label
        Label label = new Label("Start of Asteroids");
        // create circle location from top left is 300x 200y and radius is 50
        Circle circle = new Circle(300, 200, 50);
        pane.getChildren().add(label);
        pane.getChildren().add(circle);
        
        // create a scene
        Scene scene = new Scene(pane);
        stage.setTitle("Asteroids");
        stage.setScene(scene);
        // Display the stage
        stage.show();
    }
//run the application
    public static void main(String[] args) {
        launch(args);
    }
}
