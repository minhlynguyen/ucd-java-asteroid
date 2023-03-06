package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Set the size of the screen
			Pane pane = new Pane();
			pane.setPrefSize(600, 600);
			
			// create the characters
			Polygon playership = PlayerShip.createPlayership();
			pane.getChildren().add(playership);
			
			//as the level increase,add a for loop to increase asteroid 
			Polygon asteroid = Asteroid.createAsteroid();
			pane.getChildren().add(asteroid);
			
			Polygon alien = Alien.createAlien();
			pane.getChildren().add(alien);
			
			// show the scene
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
