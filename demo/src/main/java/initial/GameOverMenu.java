package asteroid_app.initial;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import static javafx.scene.text.FontWeight.*;

public class GameOverMenu {
    public static Scene gameOverMenu(int WIDTH, int HEIGHT){
        // ------ GAME OVER SCENE ------
        // GridPane gridPane = new GridPane();
        // gridPane.setAlignment(Pos.BASELINE-CENTER);
        // gridPane.setHgap(10);
        // gridPane.setVgap(30);
        // gridPane.setPadding(new Insets(0, 10, 0, 10));

        // Label nameField = new Label("Enter your name:");
        TextField nameText = new TextField();
        nameText.setPrefWidth(100);
        // gridPane.add(nameField, 0, 0);
        // gridPane.add(nameText, 1, 0);

        Label headlineover = new Label("GAME OVER");
        headlineover.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
        // Button playGameSm = new Button("PLAY GAME");
        Label yourScore = new Label("Your score is");

        // Score update later
        int topTenMin = 4000;
        int finalScore = 5000;

        Label score = new Label(""+finalScore);
        Label yourName = new Label("Enter your name");

        Button saveScore = new Button("Save");
        saveScore.setId("saveScore");
        Button seeHighScore = new Button("High Scores");
        saveScore.setId("seeHighScore");

        VBox infoBox;


        if(finalScore >= topTenMin){
            infoBox = new VBox(25, headlineover, yourScore, score, yourName, nameText, saveScore);
        }else{
            infoBox = new VBox(25, headlineover, yourScore, score, seeHighScore);
        }

        infoBox.setAlignment(Pos.CENTER);
        Scene gameOverScene= new Scene(infoBox,WIDTH*0.6,HEIGHT*0.8);

        return gameOverScene;
    }

    public static Button getButton(Button buttonName){
        return buttonName;
    }
}