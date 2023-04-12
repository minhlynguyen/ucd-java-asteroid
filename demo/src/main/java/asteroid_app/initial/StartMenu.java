package demo.src.main.java.asteroid_app.initial;

// Scene is the container for all content
import javafx.scene.Scene;
import javafx.scene.control.Label;
//import javafx.scene.control.Button to display button
import javafx.scene.control.Button;
//import VBox to manage the nodes in a pane
import javafx.scene.layout.VBox;
//import Font to set the size of labels
import javafx.scene.text.Font;
//import Fontweight to set the style of labels and buttons
import javafx.scene.text.FontWeight;
//import BorderPanet to set the position of the nodes
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;

public class StartMenu {
    
    public static Scene startMenu(int WIDTH, int HEIGHT){
        // Start Menu
        // set a border pane to ocntain and set the position of nodes
        BorderPane borderPane = new BorderPane();

        // create a new scene
        Scene startMenuScene = new Scene(borderPane, WIDTH, HEIGHT);

        // labels on the screen
        Label headline = new Label("ASTEROIDS");
        headline.setFont(Font.font("Monospaced", FontWeight.BOLD, 100));
        // final website label
        Label website = new Label("www.freevideogamesonline.com");

        // create buttons
        // play game button
        Button playGame = new Button("PLAY GAME");
        // set button id
        playGame.setId("playGame");
        // check score button
        Button highScores = new Button("HIGH SCORES");
        // set button id
        highScores.setId("highScores");


        // create a Vbox to manage the nodes on the start menu
        VBox vBox = new VBox(30, headline, playGame, highScores, website);

        // set the position of vBox
        vBox.setAlignment(Pos.CENTER);

        // add vBox into root
        borderPane.setCenter(vBox);

        return startMenuScene;
    }

    public static Button getButton(Button buttonName){
        return buttonName;
    }
}
