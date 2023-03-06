package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class PlayerShip extends Character{
	
	public static Polygon createPlayership() {
		// Initialize the size and position of the player-ship
		Polygon playership = new Polygon();
		playership.getPoints().setAll(
				-5.0, 0.0, 
				0.0, -15.0, 
				5.0, 0.0);
		
		playership.setTranslateX(300);
		playership.setTranslateY(300);
		
		return playership;

	}
	
}
