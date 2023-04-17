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
    public static Scene gameOverMenu(int WIDTH, int HEIGHT, IncrementScore score, PlayerLives lives){
        int topTenMin = 4000;
        int finalScore = score.getScore() + lives.getLives() * 1000;

        Label headlineover = new Label("GAME OVER");
        headlineover.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));

        Label yourScore = new Label("Your score is");

        Label scoreLabel = new Label("" + finalScore);
        Label livesLabel = new Label("Lives left: " + lives.getLives());
        Label yourName = new Label("Enter your name");

        TextField nameText = new TextField();
        nameText.setPrefWidth(100);

        Button saveScore = new Button("Save");
        saveScore.setId("saveScore");

        Button seeHighScore = new Button("High Scores");
        seeHighScore.setId("seeHighScore");

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
